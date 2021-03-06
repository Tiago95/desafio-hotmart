import { ChatMessage } from './../models/chat-message';
import { UsuarioService } from 'app/services/usuario.service';
import { AppUtils } from 'app/utils/app-utils';
import { Usuario } from './../models/usuario';
import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { PageTitleService } from '../core/page-title/page-title.service';
import {fadeInAnimation} from "../core/route-animation/route.animation";
import { ChatInfo } from 'app/models/chat-info';
import { ChatService } from 'app/services/chat.service';
import { ActivatedRoute } from '@angular/router';
import { DatePipe } from '@angular/common';

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

  private usuarioLogado: Usuario;

  public constructor(private route: ActivatedRoute, private chatService: ChatService, private usuarioService: UsuarioService, private datePipe: DatePipe) {

    this.usuarioLogado = usuarioService.getUsuarioLogado();
    this.chatService.getNewMessage().subscribe(chatMessage => this.setNewMessage(chatMessage));

  }

  public ngOnInit(){

    this.route.params.subscribe(params => {

      let idUserActive: number = + params['idUserActive'];

      this.chatService.getChatInfo(idUserActive).then(chatInfo => {

        this.chatInfo = chatInfo;

        if(chatInfo && chatInfo.mensagensAtivas && chatInfo.mensagensAtivas.length > 0){

          this.trabalharDadosChatInfo(idUserActive);

        }else if(idUserActive){

          this.usuarioService.findById(idUserActive).then(usuario => {

            if(usuario){

              if(this.chatInfo && this.chatInfo.usuariosComMensagens && this.chatInfo.usuariosComMensagens.length > 0){

                this.chatInfo.usuariosComMensagens.push(usuario);
                this.trabalharDadosChatInfo(idUserActive);

              }else{

                this.chatInfo = new ChatInfo();

                this.chatInfo.mensagensAtivas = new Array();
                this.chatInfo.usuariosComMensagens = new Array(usuario);

                this.trabalharDadosChatInfo(idUserActive);

              }           

            }            

          });         

        } 

       this.atualizarStatusMensagens();
      
      });

    });  

  }

  public send() {
    
    if (this.newMessage) {      

      this.chatService.sendMsg(this.newMessage, this.usuarioAtivo.id);
      this.chatInfo.mensagensAtivas.push(this.constructChatMessage());
      this.newMessage = null;

    }

  }

  public changeUserChat(idUserActive: number){

    this.chatService.getMensagensAtivasByUsuarioDestino(idUserActive).then(messages => {

      this.chatInfo.mensagensAtivas = messages;

      this.atualizarStatusMensagens();

    });

    this.chatInfo.usuariosComMensagens = this.chatInfo.usuariosComMensagens.map((usuario, index) => {

      this.setUserActive(usuario, index, idUserActive);

      return usuario;

    });

  }

  private setUserActive(usuario: Usuario, index: number, idUserActive: number){

    if(idUserActive && usuario.id === idUserActive){

      usuario['active'] = true;
      this.usuarioAtivo = usuario;

    }else if(!idUserActive && index === 0){

      usuario['active'] = true;
      this.usuarioAtivo = usuario;

    }else{

      usuario['active'] = false;

    }

  }

  private setAvatarUser(usuario: Usuario){

    usuario['avatar'] = AppUtils.getRandomInt(1, 13);

  }
  
  private constructChatMessage(): ChatMessage{

      let chatMessage: ChatMessage = new ChatMessage();

      chatMessage.idUsuarioOrigem = this.usuarioLogado.id;
      chatMessage.nameUsuarioOrigem = this.usuarioLogado.nick;
      chatMessage.message = this.newMessage;
      chatMessage.date = new Date();
      chatMessage.dateFormatter = this.datePipe.transform(chatMessage.date, 'dd-MM-yyyy HH:mm:ss');

      return chatMessage;

  }

  private setNewMessage(chatMessage: ChatMessage){

    if(this.chatInfo && this.chatInfo.mensagensAtivas
        && this.usuarioAtivo.id === chatMessage.idUsuarioOrigem){

      this.chatInfo.mensagensAtivas.push(chatMessage);

    }   

  }

  private trabalharDadosChatInfo(idUserActive: number){

    this.chatInfo.usuariosComMensagens = this.chatInfo.usuariosComMensagens.map((usuario, index) => {

      this.setUserActive(usuario, index, idUserActive);
      this.setAvatarUser(usuario);

      return usuario;

    });

    this.chatInfo.usuariosComMensagens.sort((usuario1, usuario2) => {

        return usuario1['active'] == usuario2['active'] ? 0 : usuario1['active'] ? -1 : 1;

    });

  }

  private atualizarStatusMensagens(){

    if(this.chatInfo && this.chatInfo.mensagensAtivas && this.chatInfo.mensagensAtivas.length > 0){

        let mensagensAtivasFilter: Array<ChatMessage> = this.chatInfo.mensagensAtivas.filter(chatMessage => {

        if(chatMessage.idUsuarioOrigem !== this.usuarioService.getIdUsuarioLogado()){

          return chatMessage.id;

        } 

      });

      let idsChatMessage: Array<number> = mensagensAtivasFilter.map(chatMessage => {

        return chatMessage.id;       

      });

      if(idsChatMessage && idsChatMessage.length > 0){

        this.chatService.atualizarMensagensRecebida(idsChatMessage);
        this.chatService.atualizarMensagensLida(idsChatMessage);

      }     

    }

  }
	
}



