import { TipoRetornoEnum } from './../../enum/tipo-retorno-enum';
import { EstruturaJson } from './../../models/estrutura-json';
import { HttpUtils } from './../../utils/http-utils';
import { MessageInfoVO } from './../../models/message-info-vo';
import { FieldInfoVO } from './../../models/field-info-vo';
import { AuthenticationService } from 'app/services/authentication.service';
import { Usuario } from './../../models/usuario';
import { Component, OnInit,ViewEncapsulation } from '@angular/core';
import {Router} from "@angular/router";
import { ChatService } from 'app/services/chat.service';

@Component({
   selector: 'ms-register-session',
   templateUrl:'./register-component.html',
   styleUrls: ['./register-component.scss'],
   encapsulation: ViewEncapsulation.None,
})
export class RegisterComponent {
	
  private usuario: Usuario;

  private fieldsInfoVO: Map<string, FieldInfoVO>;

  private messagesInfoVO: Array<MessageInfoVO>;

  constructor(private router: Router, private authService: AuthenticationService, private chatService: ChatService) { 

    this.usuario = new Usuario();
    this.usuario.permitirConversasAnonimas = true;
    this.fieldsInfoVO = new Map();
    this.messagesInfoVO = new Array();

  }

  public register() {

    this.authService.register(this.usuario).then(estruturaJson => {
      
        if(estruturaJson){

        if(estruturaJson.returnType && (TipoRetornoEnum[TipoRetornoEnum.SUCESSO] === estruturaJson.returnType.toString() || estruturaJson.returnType === TipoRetornoEnum.SUCESSO as TipoRetornoEnum)){

          this.chatService.connect(this.usuario.email, this.usuario.senha);
          this.router.navigate(['/dashboard']);
    
        }

        this.fieldsInfoVO = HttpUtils.getMapFieldInfosVOByEstruturaJson(estruturaJson);
        this.messagesInfoVO = estruturaJson.messagesInfo;

      }
    
    });

  }
	
}



