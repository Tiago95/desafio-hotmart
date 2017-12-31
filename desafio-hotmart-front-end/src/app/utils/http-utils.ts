import { FieldInfoVO } from './../models/field-info-vo';
import { EstruturaJson } from './../models/estrutura-json';
import { MessageInfoVO } from './../models/message-info-vo';
import { TipoRetornoEnum } from './../enum/tipo-retorno-enum';

export class HttpUtils{

    private constructor(){}

    public static getDefaultEstruturaJsonErro(): EstruturaJson{

        let estruturaJson: EstruturaJson = new EstruturaJson();
        let messageInfoVO: MessageInfoVO = new MessageInfoVO();

        messageInfoVO.message = 'Falha no servidor. Por favor entre em contato com o administrador.';
        messageInfoVO.returnType = TipoRetornoEnum.ERRO;

        estruturaJson.returnType = TipoRetornoEnum.ERRO;
        estruturaJson.messagesInfo = [messageInfoVO];

        return estruturaJson;

    }

    public static getMapFieldInfosVOByEstruturaJson(estruturaJson: EstruturaJson): Map<string, FieldInfoVO>{

        let mapReturn = new Map();

        if(estruturaJson && estruturaJson.fieldsInfo && estruturaJson.fieldsInfo.length > 0){

            for (let key in Object.keys(estruturaJson.fieldsInfo)) {

                let campoInfo = estruturaJson.fieldsInfo[key];

                mapReturn.set(campoInfo.nameField, campoInfo);
                
             }

        }

        return mapReturn;

    }

}