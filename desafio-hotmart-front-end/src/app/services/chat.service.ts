import { ChatMessage } from './../models/chat-message';
import { ChatInfo } from './../models/chat-info';
import { RequestMethod } from '@angular/http';
import { HttpControl } from './../models/http-control';
import { Injectable, OnInit } from '@angular/core';
import {Subject} from 'rxjs/Subject';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/filter';
import { Message } from 'app/models/message';
import Stomp from 'stompjs';
import SockJS from 'sockjs-client';
import { HttpService } from 'app/services/http.service';
import { DesafioHotmartAppComponent } from 'app/app.component';
import { UsuarioService } from 'app/services/usuario.service';
import { Usuario } from 'app/models/usuario';
import { Observable } from 'rxjs/Observable';
import { LastChatInfo } from 'app/models/last-chat-info';

const CHAT_URL = 'http://localhost:8080/chat';

@Injectable()
export class ChatService{

	private stompClient: any;

	private messageChange: Subject<ChatMessage> = new Subject();

	public constructor(private httpService: HttpService, private usuarioService: UsuarioService){

		if(localStorage.getItem("isSocketConnect")){

			let credentialsSocket = JSON.parse(localStorage.getItem("credentialsSocketConnect"));

			this.connect(credentialsSocket.login, credentialsSocket.password);

		}

	}

	public connect(login: string, password: string): Promise<void>{

		return new Promise<void>((resolve, reject) => {

			let ws = new SockJS(CHAT_URL);
			this.stompClient = Stomp.over(ws);
			let that = this;
			this.stompClient.connect({login: login, password: btoa(password)}, function(frame) {

				localStorage.setItem("isSocketConnect", "true");
				localStorage.setItem("credentialsSocketConnect", JSON.stringify({login: login, password: password}));

				resolve();

				that.stompClient.subscribe("/user/chatHotmart", (message) => {

					if(message.body) {

						that.messageChange.next(JSON.parse(message.body) as ChatMessage);

					}

				});
					
			}, (erro => {

				console.log(erro);

				this.connect(login, password);

			}));

		});		

	}

	public sendMsg(message: string, idUsuarioDestino: number){

		if(this.stompClient){

			this.stompClient.send("/app/chat" , {}, JSON.stringify({message: message, idUsuarioDestino: idUsuarioDestino}));

		}

	}

	public getChatInfo(idUserActive: number): Promise<ChatInfo>{

		let url = idUserActive && idUserActive > 0 ? DesafioHotmartAppComponent.API_URL + "/chat/getChatInfoByIdUser/" + this.usuarioService.getIdUsuarioLogado() + "/" + idUserActive : DesafioHotmartAppComponent.API_URL + "/chat/getChatInfoByIdUser/" + this.usuarioService.getIdUsuarioLogado();

		return new Promise<ChatInfo>((resolve, reject) => {

			this.httpService.realizarRequisicaoHttp(new HttpControl(url, RequestMethod.Get)).then(estruturaJson => {

				if(estruturaJson){

					resolve(estruturaJson.voReturn as ChatInfo);

				}else{

					resolve(null);

				}

			});

		});

	}

	public getMensagensAtivasByUsuarioDestino(idUserActive: number): Promise<Array<ChatMessage>>{

		let url = DesafioHotmartAppComponent.API_URL + "/chat/getMensagensAtivasByIdUsuarioOrigemAndUsuarioDestino/" + this.usuarioService.getIdUsuarioLogado() + "/" + idUserActive;

		return new Promise<Array<ChatMessage>>((resolve, reject) => {

			this.httpService.realizarRequisicaoHttp(new HttpControl(url, RequestMethod.Get)).then(estruturaJson => {

				if(estruturaJson){

					resolve(estruturaJson.voReturn as Array<ChatMessage>);

				}else{

					resolve(null);

				}

			});

		});

	}

	public getLastChatInfo(): Promise<LastChatInfo>{

		let url = DesafioHotmartAppComponent.API_URL + "/chat/getLastChatInfo/" + this.usuarioService.getIdUsuarioLogado();

		return new Promise<LastChatInfo>((resolve, reject) => {

			this.httpService.realizarRequisicaoHttp(new HttpControl(url, RequestMethod.Get)).then(estruturaJson => {

				if(estruturaJson){

					resolve(estruturaJson.voReturn as LastChatInfo);

				}else{

					resolve(null);

				}

			});

		});

	}

	public getNewMessage(): Observable<ChatMessage> {

		return this.messageChange.asObservable();

	}

}