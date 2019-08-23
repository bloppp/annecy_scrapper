import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from '../shared/auth.service';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CategoryDto } from './model/categoryDto';

@Injectable()
export class CategoryService {
    constructor(private authService: AuthService, private http: HttpClient) {} 
    
    
    getCategoryList(): Observable<HttpResponse<CategoryDto[]>> {
        const serviceUrl = `${environment.apiBaseUrl}/categories`;

        return this.http.get<CategoryDto[]>(serviceUrl, {
            headers: this.authService.jwtHeader,
            observe: 'response',
        });
    }
}