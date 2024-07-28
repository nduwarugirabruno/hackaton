import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable, catchError, tap, throwError } from 'rxjs';

const base_url = ['http://localhost:8081/api/v1/user/'];

@Injectable({
    providedIn: 'root',
})
export class UserService {
    private http = inject(HttpClient);

    constructor() {}

    getAll(): Observable<any> {
        return this.http.get(base_url + 'get/all').pipe(
            tap((data) => console.log('data: ', data)),
            catchError(this.handleError)
        );
    }

    private handleError(error: HttpErrorResponse) {
        if (error.error instanceof Error) {
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
