import { TipoRetornoEnum } from '../enum/tipo-retorno-enum';

export class MessageInfoVO {
  
    private _message : string;
  
    private _returnType : TipoRetornoEnum;
  

    public get message(): string {

        return this._message;

    }

    public set message(message: string) {

        this._message = message;

    }

    public get returnType(): TipoRetornoEnum {

        return this._returnType;

    }

    public set returnType(returnType: TipoRetornoEnum) {

        this._returnType = returnType;

    }  
  
}
