import { HttpUtils } from 'app/utils/http-utils';
import { MessageInfoVO } from 'app/models/message-info-vo';
import { FieldInfoVO } from './../../models/field-info-vo';
import { TipoRetornoEnum } from 'app/enum/tipo-retorno-enum';
import { EstruturaJson } from './../../models/estrutura-json';
import { AuthenticationService } from './../../services/authentication.service';
import { Component, OnInit ,ViewEncapsulation } from '@angular/core';
import {Router} from "@angular/router";
import { Credentials } from 'app/models/credentials';

@Component({
   selector: 'ms-login-session',
   templateUrl:'./login-component.html',
   styleUrls: ['./login-component.scss'],
   encapsulation: ViewEncapsulation.None,
})
export class LoginComponent {
	
  private credentials: Credentials;

  private errorMessage: string;

  private fieldsInfoVO: Map<string, FieldInfoVO>;

  private messagesInfoVO: Array<MessageInfoVO>;

  constructor(private router: Router, private authService: AuthenticationService) { 

    this.credentials = new Credentials();
    
  }

  login(){

    this.authService.login(this.credentials).then(this.callbackSucessLogin, err => {this.errorMessage="Usuário ou senha incorreta";});

  }

  private callbackSucessLogin(estruturaJson: EstruturaJson): void{

    if(estruturaJson){

      if(TipoRetornoEnum.SUCESSO === estruturaJson.returnType){

        this.router.navigate(['/chat']);
  
      }

      this.fieldsInfoVO = HttpUtils.getMapFieldInfosVOByEstruturaJson(estruturaJson);
      this.messagesInfoVO = estruturaJson.messagesInfo;

    }

  }
	
}