export class ChatMessage{

    private _idUsuarioOrigem: number;
	
	private _nameUsuarioOrigem: string;
	
	private _message: string;
	
	private _date: Date;    
	
	private _dateFormatter: string;

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

	public get date(): Date {
		return this._date;
	}

	public set date(value: Date) {
		this._date = value;
	}

	public get dateFormatter(): string {
		return this._dateFormatter;
	}

	public set dateFormatter(value: string) {
		this._dateFormatter = value;
	}	   

}