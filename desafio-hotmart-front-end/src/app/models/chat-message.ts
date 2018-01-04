export class ChatMessage{

    private _idUsuarioOrigem: number;
	
	private _nameUsuarioOrigem: string;
	
	private _message: string;
	
    private _date: string;    

	public get idUsuarioOrigem(): number {
		return this._idUsuarioOrigem;
	}

	public set idUsuarioOrigem(value: number) {
		this._idUsuarioOrigem = value;
	}

	public get nameUsuarioOrigem(): string {
		return this._nameUsuarioOrigem;
	}

	public set nameUsuarioOrigem(value: string) {
		this._nameUsuarioOrigem = value;
	}    

	public get message(): string {
		return this._message;
	}

	public set message(value: string) {
		this._message = value;
	}

	public get date(): string {
		return this._date;
	}

	public set date(value: string) {
		this._date = value;
	}    

}