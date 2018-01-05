import { TipoRetornoEnum } from './../enum/tipo-retorno-enum';
import { EstruturaJson } from 'app/models/estrutura-json';
import { Usuario } from './../models/usuario';
import { RequestMethod } from '@angular/http';
import { HttpControl } from './../models/http-control';
import { HttpService } from "app/services/http.service";
import { Injectable } from "@angular/core";
import { DesafioHotmartAppComponent } from 'app/app.component';
import { Contato } from 'app/models/contato';

@Injectable()
export class UsuarioService{

    public constructor(private httpService: HttpService){}

    public getContatosByUserLogado(): Promise<Array<Contato>>{

        return new Promise<Array<Contato>>((resolve, reject) => {

            this.httpService.realizarRequisicaoHttp(new HttpControl(DesafioHotmartAppComponent.API_URL + "/contacts/getAllContactsByIdUser/" + this.getIdUsuarioLogado(), RequestMethod.Get))
            .then(estruturaJson => {           

                if(estruturaJson && estruturaJson.returnType && (estruturaJson.returnType.toString() === TipoRetornoEnum[TipoRetornoEnum.SUCESSO] || estruturaJson.returnType === TipoRetornoEnum.SUCESSO as TipoRetornoEnum)){

                    resolve(estruturaJson.voReturn as Array<Contato>);
    
                }else{
    
                   resolve();
    
                }

            });           

        });

    }
    
    public bloquear(idUsuario: number): Promise<EstruturaJson>{

        return this.httpService.realizarRequisicaoHttp(new HttpControl(DesafioHotmartAppComponent.API_URL + "/user/bloquear/" + this.getIdUsuarioLogado() + "/" + idUsuario, RequestMethod.Post))

    }

    public desbloquear(idUsuario: number): Promise<EstruturaJson>{

        return this.httpService.realizarRequisicaoHttp(new HttpControl(DesafioHotmartAppComponent.API_URL + "/user/desbloquear/" + this.getIdUsuarioLogado() + "/" + idUsuario, RequestMethod.Post))

    }

    public solicitarAmizade(idUsuario: number): Promise<EstruturaJson>{

        return this.httpService.realizarRequisicaoHttp(new HttpControl(DesafioHotmartAppComponent.API_URL + "/user/solicitarAmizade/" + this.getIdUsuarioLogado() + "/" + idUsuario, RequestMethod.Post))
        
    }

    public aceitarAmizade(idUsuario: number): Promise<EstruturaJson>{

        return this.httpService.realizarRequisicaoHttp(new HttpControl(DesafioHotmartAppComponent.API_URL + "/user/atualizarPedidoAmizade/" + idUsuario + "/" + this.getIdUsuarioLogado() + "/ACEITO" , RequestMethod.Post))
        
    }

    public recusarAmizade(idUsuario: number): Promise<EstruturaJson>{

        return this.httpService.realizarRequisicaoHttp(new HttpControl(DesafioHotmartAppComponent.API_URL + "/user/atualizarPedidoAmizade/" + idUsuario + "/" + this.getIdUsuarioLogado() + "/RECUSADO", RequestMethod.Post))
        
    }

    public getUsuarioLogado(): Usuario{

        return JSON.parse(localStorage.getItem("currentUser")) as Usuario;

    }

    public getIdUsuarioLogado(): number{

        return this.getUsuarioLogado().id;

    }

    public findById(idUsuario: number): Promise<Usuario>{

        return new Promise<Usuario>((resolve, reject) => {

            return this.httpService.realizarRequisicaoHttp(new HttpControl(DesafioHotmartAppComponent.API_URL + "/user/findById/" + idUsuario, RequestMethod.Get)).then(estruturaJson => {

                if(estruturaJson && (estruturaJson.returnType.toString() === TipoRetornoEnum[TipoRetornoEnum.SUCESSO] || estruturaJson.returnType === TipoRetornoEnum.SUCESSO as TipoRetornoEnum)
                    && estruturaJson.voReturn){

                    resolve(estruturaJson.voReturn as Usuario);

                }

                resolve(null);

            });

        });        

    }

}