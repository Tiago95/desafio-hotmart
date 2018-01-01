export class Message{

    private _name: string;

    private _message: string;

    private _date: string;

	public get name(): string {
		return this._name;
	}

	public set name(value: string) {
		this._name = value;
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