import { Usuario } from './../models/usuario';
import { EstruturaJson } from 'app/models/estrutura-json';
import { HttpControl } from './../models/http-control';
import { DesafioHotmartAppComponent } from './../app.component';
import { Http, Headers, RequestOptions, Response, RequestMethod } from '@angular/http';
import { Injectable } from "@angular/core";
import 'rxjs/add/operator/map';

import { Credentials } from './../models/credentials';
import { HttpService } from './http-service';

@Injectable()
export class AuthenticationService{

    constructor(private httpService: HttpService){}

    public login(credentials: Credentials): Promise<EstruturaJson> {
        
        return new Promise<EstruturaJson>((resolve, reject) => {

            this.httpService.realizarRequisicaoHttp(new HttpControl(DesafioHotmartAppComponent.API_URL + "/auth/user", RequestMethod.Get, null, this.createHeaders(credentials)))
            .then(response => {
                
                if(response){

                    let usuario = response.voReturn as Usuario;
                    
                    if (usuario) {
                        
                        localStorage.setItem('currentUser', JSON.stringify(usuario));
        
                    }
        
                }

                resolve(response);

            });
       
        });         

    }

    public register(usuario: Usuario): Promise<EstruturaJson>{

        return this.httpService.realizarRequisicaoHttp(new HttpControl(DesafioHotmartAppComponent.API_URL + "/auth/register", RequestMethod.Post, usuario));

    }

    private createHeaders(credentials: Credentials): Headers{

        let headers = new Headers();

        headers.append('Accept', 'application/json')
        headers.append("Authorization", "Basic " + btoa(credentials.username+ ':' + credentials.password)); 

        return headers;

    }

}