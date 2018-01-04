import { Usuario } from './../models/usuario';
import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { PageTitleService } from '../core/page-title/page-title.service';
import {fadeInAnimation} from "../core/route-animation/route.animation";
import { ChatInfo } from 'app/models/chat-info';
import { ChatService } from 'app/services/chat.service';

@Component({
   selector: 'ms-chat',
   templateUrl:'./chat-component.html',
   styleUrls: ['./chat-component.scss'],
   encapsulation: ViewEncapsulation.None,
   host: {
    "[@fadeInAnimation]": 'true'
  },
  animations: [ fadeInAnimation ]
})
export class ChatComponent implements OnInit{
  
  private chatInfo: ChatInfo;

  private usuarioAtivo: Usuario;

  private newMessage: string; 

  public constructor(private chatService: ChatService) {}

  public ngOnInit(){

    this.chatService.getChatInfo(null).then(chatInfo => this.chatInfo = chatInfo);

  }

  send() {
    if (this.newMessage) {
      //this.messages.push({
       // msg: this.newMessage,
        //from: 'me',
        //time: '2 sec ago'
      //});
      this.newMessage = '';
    }
  }
	
}



