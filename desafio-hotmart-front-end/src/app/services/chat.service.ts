import { ChatInfo } from './../models/chat-info';
import { RequestMethod } from '@angular/http';
import { HttpControl } from './../models/http-control';
import {Injectable} from '@angular/core';
import {Subject} from 'rxjs/Subject';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/filter';
import { Message } from 'app/models/message';
import Stomp from 'stompjs';
import SockJS from 'sockjs-client';
import { ChatInfo } from 'app/models/chat-info';
import { HttpService } from 'app/services/http.service';
import { DesafioHotmartAppComponent } from 'app/app.component';
import { UsuarioService } from 'app/services/usuario.service';

const CHAT_URL = 'http://localhost:8080/chat';

@Injectable()
export class ChatService {

	private stompClient: any;

	public constructor(private httpService: HttpService, private usuarioService: UsuarioService){}

	public connect(login: string, password: string){

			let ws = new SockJS(CHAT_URL);
			this.stompClient = Stomp.over(ws);
			let that = this;
			this.stompClient.connect({login: login, password: btoa(password)}, function(frame) {
				that.stompClient.subscribe("/user/chatHotmart", (message) => {
						if(message.body) {					  
							console.log(message.body);
						}
					});
					
			});

	}

	public sendMsg(message: string, idUsuarioDestino: number){

		this.stompClient.send("/app/chat" , {}, JSON.stringify({message: message, idUsuarioDestino: idUsuarioDestino}));

	}

	public getChatInfo(idUserActive: number): Promise<ChatInfo>{

		let url = idUserActive && idUserActive > 0 ? DesafioHotmartAppComponent.API_URL + "/getChatInfoByIdUser/" + this.usuarioService.getIdUsuarioLogado() + "/" + idUserActive : DesafioHotmartAppComponent.API_URL + "/getChatInfoByIdUser/" + this.usuarioService.getIdUsuarioLogado();

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

}