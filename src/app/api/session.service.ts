import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from '../shared/auth.service';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { SessionDto } from './model/sessionDto';

@Injectable()
export class SessionService {
    constructor(private authService: AuthService, private http: HttpClient) {} 
    
    
    getAllSession(): Observable<HttpResponse<SessionDto[]>> {
        const serviceUrl = `${environment.apiBaseUrl}/sessions`;

        return this.http.get<SessionDto[]>(serviceUrl, {
            headers: this.authService.jwtHeader,
            observe: 'response',
        });
    }
}