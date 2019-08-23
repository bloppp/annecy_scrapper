export class CookieSettings {
    jwt: string;
    token: string;

    constructor(data: { jwt?: string, token?: string }) {
        this.jwt   = data.jwt;
        this.token = data.token;
    }
}