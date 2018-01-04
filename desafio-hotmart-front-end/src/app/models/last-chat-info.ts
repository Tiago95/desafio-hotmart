import { ChatMessage } from 'app/models/chat-message';

export class LastChatInfo{

    private _quantidadeMensagensNaoLidas: number;
    
    private _ultimasMensagensRecebidas: Array<ChatMessage>;

	public get quantidadeMensagensNaoLidas(): number {
		return this._quantidadeMensagensNaoLidas;
	}

	public set quantidadeMensagensNaoLidas(value: number) {
		this._quantidadeMensagensNaoLidas = value;
	}

	public get ultimasMensagensRecebidas(): Array<ChatMessage> {
		return this._ultimasMensagensRecebidas;
	}

	public set ultimasMensagensRecebidas(value: Array<ChatMessage>) {
		this._ultimasMensagensRecebidas = value;
	}	

}