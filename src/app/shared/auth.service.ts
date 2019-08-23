import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';
import { CookiesStorageService } from 'ngx-store';
import { Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CookieSettings } from './cookies-setting.class';
import { Injectable } from '@angular/core';
import { User } from '../domain/User';

@Injectable()
export class AuthService {
    constructor(private cookiesStorageService: CookiesStorageService, private http: HttpClient) {}

    checkTokens(): Observable<HttpResponse<User>> {
        const serviceUrl = environment.apiBaseUrl + '/users/validate-token';

        return this.http.get<User>(serviceUrl, { headers: this.authHeaders, observe: 'response' })
    }

    get authHeaders(): HttpHeaders {
        const cookieSettings = this.cookieSettings;

        return new HttpHeaders({ 'Access-Token': cookieSettings.token, 'JW-Token': cookieSettings.jwt });
    }

    get jwtHeader(): HttpHeaders {
        const cookieSettings = this.cookieSettings;

        return new HttpHeaders({ 'JW-Token': environment.jwt });
    }

    get cookieSettings(): CookieSettings {
        const settings = this.cookiesStorageService.get('SETTINGS');

        return !!settings ? new CookieSettings(settings) : new CookieSettings({ token: '', jwt: '' });
    }

    set cookieSettings(settings: CookieSettings) {
        if (!!settings) {
            this.cookiesStorageService.set('SETTINGS', settings);
        } else {
            this.cookiesStorageService.remove('SETTINGS');
        }
    }
}