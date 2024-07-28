import {
    HttpErrorResponse,
    HttpEvent,
    HttpHandler,
    HttpInterceptor,
    HttpRequest,
    HttpResponse,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';
import { ToastrService } from 'ngx-toastr';
import { Observable, catchError, finalize, map, throwError } from 'rxjs';

@Injectable()
export class HttpSpinnerInterceptor implements HttpInterceptor {
    constructor(
        private spinner: NgxSpinnerService,
        private toastr: ToastrService
    ) {}

    intercept(
        req: HttpRequest<any>,
        next: HttpHandler
    ): Observable<HttpEvent<any>> {
        this.spinner.show();
        this.toastr.info('Request Initialized');

        // Pass the cloned request with the updated header to the next handler
        return next.handle(req).pipe(
            map((event: HttpEvent<any>) => {
                if (event instanceof HttpResponse) {
                    this.toastr.success('Request successful');
                }
                return event;
            }),
            catchError((err: any) => {
                if (err instanceof HttpErrorResponse) {
                    // Handle HTTP errors
                    if (err.status === 401) {
                        // Specific handling for unauthorized errors
                        console.error('Unauthorized request:', err);
                        this.toastr.error(
                            `Unauthorized request: ${err.message}`
                        );
                        // You might trigger a re-authentication flow or redirect the user here
                    } else {
                        // Handle other HTTP error codes
                        console.error('HTTP error:', err);
                        this.toastr.error(`HTTP error: ${err.message}`);
                    }
                } else {
                    // Handle non-HTTP errors
                    console.error('An error occurred:', err);
                    this.toastr.error(`An error occurred: ${err.message}`);
                }

                // Re-throw the error to propagate it further
                return throwError(() => err);
            }),
            finalize(() => {
                setTimeout(() => {
                    this.spinner.hide();
                }, 1000);
            })
        );
    }
}
