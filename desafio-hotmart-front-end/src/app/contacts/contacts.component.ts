import { Router } from '@angular/router';
import { TipoRetornoEnum } from 'app/enum/tipo-retorno-enum';
import { AppUtils } from './../utils/app-utils';
import { UsuarioService } from './../services/usuario.service';
import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {fadeInAnimation} from "../core/route-animation/route.animation";
import { Contato } from 'app/models/contato';

@Component({
  selector: 'ms-contacts',
  templateUrl:'./contacts-component.html',
  styleUrls: ['./contacts-component.scss'],
  encapsulation: ViewEncapsulation.None,
    host: {
        "[@fadeInAnimation]": 'true'
    },
    animations: [ fadeInAnimation ]
})
export class ContactsComponent implements OnInit{

    private contatos: Array<Contato>;

    public constructor(private usuarioService: UsuarioService, private router: Router){}

    public ngOnInit(){

        this.usuarioService.getContatosByUserLogado().then(contatos => {
            
            if(contatos && contatos.length > 0){

                this.contatos = contatos.map(contato => {

                    contato['avatar'] = AppUtils.getRandomInt(1, 13);
    
                    return contato;
    
                });

            }            

        }, error => {this.contatos = null; console.log(error)}).catch(error => {this.contatos = null; console.log(error)});

    }

    public bloquear(idUsuario: number){

        this.usuarioService.bloquear(idUsuario).then(estruturaJson => {
            
            if(estruturaJson && estruturaJson.returnType && estruturaJson.returnType.toString() === TipoRetornoEnum[TipoRetornoEnum.SUCESSO]){

                this.contatos = this.contatos.map(contato => {                    

                    if(contato.id === idUsuario){
        
                        contato.bloqueado = true;                        
        
                        return contato;
        
                    }else{
        
                        return contato;
        
                    }
        
                })

            }
            
        });

    }

    public desbloquear(idUsuario: number){        

        this.usuarioService.desbloquear(idUsuario).then(estruturaJson => {
            
            if(estruturaJson && estruturaJson.returnType && estruturaJson.returnType.toString() === TipoRetornoEnum[TipoRetornoEnum.SUCESSO]){

                this.contatos = this.contatos.map(contato => {

                    if(contato.id === idUsuario){
        
                        contato.bloqueado = false;
        
                        return contato;
        
                    }else{
        
                        return contato;
        
                    }
        
                })
                
            }

        });

    }

    public solicitarAmizade(idUsuario: number){

        this.usuarioService.solicitarAmizade(idUsuario).then(estruturaJson => {
            
            if(estruturaJson && estruturaJson.returnType && estruturaJson.returnType.toString() === TipoRetornoEnum[TipoRetornoEnum.SUCESSO]){

                this.contatos = this.contatos.map(contato => {

                    if(contato.id === idUsuario){
        
                        contato.amizadeSolicitada = true;
                        contato.statusSolicitacaoAmizade = "PENDENTE";
        
                        return contato;
        
                    }else{
        
                        return contato;
        
                    }
        
                })
                
            }

        });
        
    }

    public aceitarAmizade(idUsuario: number){        

        this.usuarioService.aceitarAmizade(idUsuario).then(estruturaJson => {
            
            if(estruturaJson && estruturaJson.returnType && estruturaJson.returnType.toString() === TipoRetornoEnum[TipoRetornoEnum.SUCESSO]){

                this.contatos = this.contatos.map(contato => {

                    if(contato.id === idUsuario){
        
                        contato.amizadeSolicitada = true;
                        contato.statusSolicitacaoAmizade = "ACEITO";
        
                        return contato;
        
                    }else{
        
                        return contato;
        
                    }
        
                })
                
            }

        });
        
    }

    public recusarAmizade(idUsuario: number){

        this.usuarioService.recusarAmizade(idUsuario).then(estruturaJson => {
            
            if(estruturaJson && estruturaJson.returnType && estruturaJson.returnType.toString() === TipoRetornoEnum[TipoRetornoEnum.SUCESSO]){

                this.contatos = this.contatos.map(contato => {

                    if(contato.id === idUsuario){
        
                        contato.amizadeSolicitada = true;
                        contato.statusSolicitacaoAmizade = "RECUSADO";
        
                        return contato;
        
                    }else{
        
                        return contato;
        
                    }
        
                })

            }

        });
        
    }

    public chat(idUsuario: number){

        this.router.navigate(["/chat", idUsuario]);
        
    }
  
}