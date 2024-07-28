import {
    HttpClient,
    HttpErrorResponse,
    HttpHeaders,
} from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable, catchError, tap, throwError } from 'rxjs';

const base_url = ['http://localhost:8081/api/v1/auth/'];

@Injectable({
    providedIn: 'root',
})
export class AuthService {
    private http = inject(HttpClient);

    constructor() {}

    authenticate(authRequest: any): Observable<any> {
        return this.http.post(base_url + 'authenticate', authRequest).pipe(
            tap((data) => console.log('data: ', data)),
            catchError(this.handleError)
        );
    }

    register(registerRequest: any): Observable<any> {
        return this.http.post(base_url + 'register', registerRequest).pipe(
            tap((data) => console.log('data: ', data)),
            catchError(this.handleError)
        );
    }

    hello(): Observable<any> {
        return this.http
            .get('http://localhost:8081/api/v1/demo', { responseType: 'text'})
            .pipe(
                tap((data) => console.log('data: ', data)),
                catchError(this.handleError)
            );
    }

    private createAuthorizationHeaders() {
        const jwtToken = localStorage.getItem('jwtToken');
        if (jwtToken) {
            console.log(`JWT token found in local storage ${jwtToken}`);
            return new HttpHeaders().set('Authorization', `Bearer ${jwtToken}`);
        }
        console.log(`JWT token not found in local storage`);
        return new HttpHeaders();
    }

    private handleError(error: HttpErrorResponse) {
        if (error.error instanceof ErrorEvent) {
            console.error('An error has occured', error.error.message);
        } else {
            console.error(
                `Back error code: ${error.status}`,
                `Error body: ${error.error}`
            );
        }
        return throwError(() => 'Try again later please');
    }
}
