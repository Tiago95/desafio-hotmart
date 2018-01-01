import {Injectable} from '@angular/core';
import {Subject} from 'rxjs/Subject';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/filter';
import { Message } from 'app/models/message';
import Stomp from 'stompjs';
import SockJS from 'sockjs-client';

const CHAT_URL = 'http://localhost:8080/chat';

@Injectable()
export class ChatService {

	private stompClient: any;

	public connect(login: string, password: string){

		let connect = localStorage.getItem('connect');

		if(!connect || connect.toString() !== "true"){

			let ws = new SockJS(CHAT_URL);
			this.stompClient = Stomp.over(ws);
			let that = this;
			this.stompClient.connect(login,	password, function(frame) {
				that.stompClient.subscribe("/chatHotmart", (message) => {
					if(message.body) {					  
					  console.log(message.body);
					}
				  });				
				localStorage.setItem('connect', 'true');
			});

		}

	}

}