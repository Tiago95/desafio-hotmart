export class Usuario {

    private _nome: string;

    private _nick: string;

    private _email: string;

    private _senha: string;

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

	public get email(): string {
		return this._email;
	}

	public set email(value: string) {
		this._email = value;
	}

	public get senha(): string {
		return this._senha;
	}

	public set senha(value: string) {
		this._senha = value;
	} 

	public toJSON(){
		return {
			nome: this.nome,
			nick: this.nick,
			email: this.email,
			senha: this.senha
		};
	}

}