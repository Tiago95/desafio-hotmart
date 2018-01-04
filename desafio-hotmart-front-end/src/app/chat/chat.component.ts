import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { PageTitleService } from '../core/page-title/page-title.service';
import {fadeInAnimation} from "../core/route-animation/route.animation";

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
export class ChatComponent {
	
  newMessage: string;

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

  constructor() {}
	
}



