import { TipoRetornoEnum } from './../enum/tipo-retorno-enum';
import { MessageInfoVO } from './message-info-vo';
import { FieldInfoVO } from './field-info-vo';

export class EstruturaJson {
  
    private _voReturn: any;

    private _returnType: TipoRetornoEnum;

    private _fieldsInfo: Array<FieldInfoVO>;

    private _messagesInfo: Array<MessageInfoVO>; 
  
    public get voReturn() : any{
      
      return this._voReturn;
      
    }
    
    public set voReturn(voReturn : any){
      
      this._voReturn = voReturn;
      
    }
  
    public get returnType() : TipoRetornoEnum{
        
        return this._returnType;
        
    }
  
    public set returnType(returnType : TipoRetornoEnum){
      
      this._returnType = returnType;
      
    }
    
    public get fieldsInfo() : Array<FieldInfoVO>{
        
        return this._fieldsInfo;
        
    }
  
    public set fieldsInfo(fieldsInfo : Array<FieldInfoVO>){
      
      this._fieldsInfo = fieldsInfo;
      
    }
    
    public get messagesInfo() : Array<MessageInfoVO>{
        
        return this._messagesInfo;
        
    }
  
    public set messagesInfo(messagesInfo : Array<MessageInfoVO>){
      
      this._messagesInfo = messagesInfo;
      
    }
  
}
