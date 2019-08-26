import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from '../shared/auth.service';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { LocationDto } from './model/location.dto';

@Injectable()
export class LocationService {
    constructor(private authService: AuthService, private http: HttpClient) {} 
    
    
    getLocationList(): Observable<HttpResponse<LocationDto []>> {
        const serviceUrl = `${environment.apiBaseUrl}/categories`;

        return this.http.get<LocationDto[]>(serviceUrl, {
            headers: this.authService.jwtHeader,
            observe: 'response',
        });
    }
}