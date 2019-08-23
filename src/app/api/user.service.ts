import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from '../shared/auth.service';
import { environment } from 'src/environments/environment';
import { User } from '../domain/User';

@Injectable()
export class UserService {
    constructor(private authService: AuthService, private http: HttpClient) {} 
    
    
    createUser(user : User) {
        const serviceUrl = `${environment.apiBaseUrl}/users`;

        this.http.post(serviceUrl, user, {
            headers: this.authService.jwtHeader,
            observe: 'response',
        }).subscribe(
            () => {
                console.log('User saved');
            },
            (error) => {console.error("Error :" + error);
            }
        );
    }
}