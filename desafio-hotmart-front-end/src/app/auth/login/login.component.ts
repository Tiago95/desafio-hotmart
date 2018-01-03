import { ChatService } from 'app/services/chat.service';
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

  public constructor(private router: Router, private authService: AuthenticationService,
                     private chatService: ChatService) { 

    this.credentials = new Credentials();
    
  }

  public login(){

    this.authService.login(this.credentials).then(estruturaJson => {

      if(estruturaJson){

        if(TipoRetornoEnum.ERRO === estruturaJson.returnType){

          this.errorMessage = "Usuário ou senha incorreta";        
    
        }else{
  
          this.chatService.connect(this.credentials.username, this.credentials.password);
          this.router.navigate(['/dashboard']);
  
        }     
  
      }

    });

  }
	
}