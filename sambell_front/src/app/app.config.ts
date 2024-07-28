import { ApplicationConfig, importProvidersFrom, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';

import { HTTP_INTERCEPTORS, provideHttpClient, withFetch, withInterceptors, withInterceptorsFromDi } from '@angular/common/http';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';
import { BrowserAnimationsModule, provideNoopAnimations } from '@angular/platform-browser/animations';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { NgxSpinnerModule } from "ngx-spinner";
import { ToastrModule } from "ngx-toastr";
import { routes } from './app.routes';
import { customInterceptor } from './services/interceptors/custom/custom.interceptor';
import { HttpSpinnerInterceptor } from './services/interceptors/spinner.interceptor';

export const appConfig: ApplicationConfig = {
    providers: [
        provideZoneChangeDetection({eventCoalescing: true}),
        provideRouter(routes),
        provideHttpClient(withInterceptorsFromDi(), withFetch()),
        {
            provide: HTTP_INTERCEPTORS,
            useClass: HttpSpinnerInterceptor,
            multi: true
        },
        importProvidersFrom(
            BrowserModule,
            BrowserAnimationsModule,
            ToastrModule.forRoot({
                timeOut: 5000,
                positionClass: 'toast-top-left',
                preventDuplicates: true
            }),
            NgxSpinnerModule.forRoot({ type: 'ball-scale-multiple' })
        ),
        provideNoopAnimations(),
        provideHttpClient(withInterceptors([customInterceptor])),
        provideClientHydration(), provideAnimationsAsync()
    ]
};
