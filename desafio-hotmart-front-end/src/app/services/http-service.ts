/* Angular Modules */
import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Http } from '@angular/http';
import { DesafioHotmartAppComponent } from 'app/app.component';
import { EstruturaJson } from 'app/models/estrutura-json';
import { HttpControl } from './../models/http-control';
import { HttpUtils } from 'app/utils/http-utils';

/**
 * Classe responsável por armazenar as regras de 
 * negócio das requisições HTTP.
 * 
 * 
 */
@Injectable()
export class HttpService {
  
    private httpService : Http;
    
    /**
     * Construtor com todos os parametros.
     * 
     * 
     */
    public constructor(private http: Http) {
      
        this.httpService = http;
      
    }
    
    /**
     * Responsável por realizar uma requisição HTTP.
     * 
     */
    public realizarRequisicaoHttp(httpControl: HttpControl) : Promise<EstruturaJson> {
        
        return new Promise<EstruturaJson>((resolve, reject) => {
       
            this.httpService.request(httpControl.url, httpControl.toRequestOptions())
            .toPromise()
            .then(response => resolve(response.json() as EstruturaJson), error => {console.log(error); resolve(HttpUtils.getDefaultEstruturaJsonErro());})
            .catch(error => {console.log(error); resolve(HttpUtils.getDefaultEstruturaJsonErro());});
       
        });     
      
    }  

}
