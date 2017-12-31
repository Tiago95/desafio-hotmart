import { TipoRetornoEnum } from 'app/enum/tipo-retorno-enum';

export class FieldInfoVO {
  
    private _message: string;

    private _nameField: string;

    private _returnType: TipoRetornoEnum;
    
    public get message(){
      
      return this._message;
      
    }
    
    public set message(message : string){
      
      this._message = message;
      
    }
    
    public get nameField(){
      
      return this._nameField;
      
    }
    
    public set nameField(nameField : string){
      
      this._nameField = nameField;
      
    }

    public get returnType(): TipoRetornoEnum {

          return this._returnType;
          
    }

    public set tipoRetorno(returnType: TipoRetornoEnum) {

          this._returnType = returnType;
          
    }    
    
  }
  