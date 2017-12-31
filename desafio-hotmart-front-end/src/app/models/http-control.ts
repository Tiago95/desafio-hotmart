import { RequestOptionsArgs, RequestMethod, Headers } from '@angular/http';
import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { RequestOptions } from '@angular/http/src/base_request_options';

/**
 * Classe responsável por armazenar os atributos de uma requisição HTTP
 * 
 */
export class HttpControl {
  
    private _method : string|RequestMethod;

    private _url : string;

    private _body ?: any;

    private _headers: Headers;
  
    /**
     * Construtor com todos os parametros.
     * 
     */ 
    public constructor(url : string, method : string|RequestMethod, body?: any, headers?: Headers){
      
      this.url = url;
      this.method = method;
      this.body = body;
      this.headers = headers;
      
    }
  
    public get method(){
      
      return this._method;
      
    }
  
    public set method(method : string|RequestMethod){
      
      this._method = method;
      
    }
  
    public get url(){
      
      return this._url;
      
    }
  
    public set url(url : string){
      
      this._url = url;
      
    }
  
    public get body(){
      
      return this._body;
      
    }
  
    public set body(body : string){
      
      this._body = body;
      
    }
    
    public get headers(): Headers {

        return this._headers;

    }

    public set headers(value: Headers) {

      this._headers = value;

    }  

    /**
     * Responsável por retornar um objeto de opções de requisição.
     * 
     */  
    public toRequestOptions() : RequestOptionsArgs {
        
        return {
          
          url: this.url,
          method: this.method,
          body: this.body,
          headers: this.headers
          
        }       
        
    }
  
}
