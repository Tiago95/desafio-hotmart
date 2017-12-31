import { EstruturaJson } from 'app/models/estrutura-json';
import { HttpControl } from './../models/http-control';
import { DesafioHotmartAppComponent } from './../app.component';
import { Http, Headers, RequestOptions, Response, RequestMethod } from '@angular/http';
import { Injectable } from "@angular/core";
import 'rxjs/add/operator/map';

import { Credentials } from './../models/credentials';
import { Usuario } from 'app/models/usuario';
import { HttpService } from './http-service';

@Injectable()
export class AuthenticationService{

    constructor(private httpService: HttpService){}

    public login(credentials: Credentials): Promise<EstruturaJson> {       

        return this.httpService.realizarRequisicaoHttp(new HttpControl(DesafioHotmartAppComponent.API_URL + "/auth/login", RequestMethod.Get, null, this.createHeaders(credentials)));

    }

    public register(usuario: Usuario): Promise<EstruturaJson>{

        return this.httpService.realizarRequisicaoHttp(new HttpControl(DesafioHotmartAppComponent.API_URL + "/auth/register", RequestMethod.Post, usuario));

    }

    private createMapRequestLogin(response: Response): void {

        if(response){

            let user = response.json().principal;
            
            if (user) {
                
                localStorage.setItem('currentUser', JSON.stringify(user));

            }

        }
        
    }

    private createRequestOption(credentials: Credentials): RequestOptions{
        
        let options = new RequestOptions();
              
        options.headers = this.createHeaders(credentials);

        return options;

    }

    private createHeaders(credentials: Credentials): Headers{

        let headers = new Headers();

        headers.append('Accept', 'application/json')
        headers.append("Authorization", "Basic " + btoa(credentials.username+ ':' + credentials.password)); 

        return headers;

    }

}