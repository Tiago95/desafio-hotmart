export class Contato{

    private _id: number;
	
	private _nome: string;
	
	private _nick: string;
	
	private _bloqueado: boolean;
	
	private _statusSolicitacaoAmizade: string;
	
    private _amizadeSolicitada: boolean;    

	public get id(): number {
		return this._id;
	}

	public set id(value: number) {
		this._id = value;
	}

	public get nome(): string {
		return this._nome;
	}

	public set nome(value: string) {
		this._nome = value;
	}

	public get nick(): string {
		return this._nick;
	}

	public set nick(value: string) {
		this._nick = value;
	}

	public get bloqueado(): boolean {
		return this._bloqueado;
	}

	public set bloqueado(value: boolean) {
		this._bloqueado = value;
	}

	public get statusSolicitacaoAmizade(): string {
		return this._statusSolicitacaoAmizade;
	}

	public set statusSolicitacaoAmizade(value: string) {
		this._statusSolicitacaoAmizade = value;
	}

	public get amizadeSolicitada(): boolean {
		return this._amizadeSolicitada;
	}

	public set amizadeSolicitada(value: boolean) {
		this._amizadeSolicitada = value;
	}    

}