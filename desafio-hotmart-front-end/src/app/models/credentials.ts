export class Credentials{

    private _username: string;

    private _password: string;

    public get username(): string{

        return this._username;

    }

    public set username(username: string){

        this._username = username;

    }

    public get password(): string{

        return this._password;

    }

    public set password(password: string){

        this._password = password;

    }
                
}