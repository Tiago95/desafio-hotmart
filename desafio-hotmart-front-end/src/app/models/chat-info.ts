import { Usuario } from './usuario';
import { ChatMessage } from 'app/models/chat-message';

export class ChatInfo{

    private _usuariosComMensagens: Array<Usuario>;

    private _mensagensAtivas: Array<ChatMessage>;

	public get usuariosComMensagens(): Array<Usuario> {
		return this._usuariosComMensagens;
	}

	public set usuariosComMensagens(value: Array<Usuario>) {
		this._usuariosComMensagens = value;
	}

	public get mensagensAtivas(): Array<ChatMessage> {
		return this._mensagensAtivas;
	}

	public set mensagensAtivas(value: Array<ChatMessage>) {
		this._mensagensAtivas = value;
	}    

}