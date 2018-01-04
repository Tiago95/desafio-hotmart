import { EstruturaJson } from './../../models/estrutura-json';
import { HttpUtils } from './../../utils/http-utils';
import { MessageInfoVO } from './../../models/message-info-vo';
import { FieldInfoVO } from './../../models/field-info-vo';
import { AuthenticationService } from 'app/services/authentication.service';
import { Usuario } from './../../models/usuario';
import { Component, OnInit,ViewEncapsulation } from '@angular/core';
import {Router} from "@angular/router";
import { TipoRetornoEnum } from 'app/enum/tipo-retorno-enum';

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

  constructor(private router: Router, private authService: AuthenticationService) { 

    this.usuario = new Usuario();
    this.fieldsInfoVO = new Map();
    this.messagesInfoVO = new Array();

  }

  public register() {

    this.authService.register(this.usuario).then(estruturaJson => {
      
        if(estruturaJson){

        if(estruturaJson.returnType && TipoRetornoEnum[TipoRetornoEnum.SUCESSO] === estruturaJson.returnType.toString()){

          this.router.navigate(['/dashboard']);
    
        }

        this.fieldsInfoVO = HttpUtils.getMapFieldInfosVOByEstruturaJson(estruturaJson);
        this.messagesInfoVO = estruturaJson.messagesInfo;

      }
    
    });

  }

  private callbackSucessRegister(estruturaJson: EstruturaJson): void{

    if(estruturaJson){

      if(TipoRetornoEnum.SUCESSO === estruturaJson.returnType){

        this.router.navigate(['/chat']);
  
      }

      this.fieldsInfoVO = HttpUtils.getMapFieldInfosVOByEstruturaJson(estruturaJson);
      this.messagesInfoVO = estruturaJson.messagesInfo;

    }

  }
	
}



