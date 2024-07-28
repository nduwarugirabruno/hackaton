import { Routes } from '@angular/router';
import { AuthComponent } from "./auth.component";

export const routes: Routes = [
    {
        path: '',
        component: AuthComponent,
        children: [
            {
                path: 'signin',
                title: 'Authentification | Connexion',
                loadComponent: () =>
                    import('./signin/signin.component').then((m) => m.SigninComponent),
            }, {
                path: 'signup',
                title: 'Authentification | Inscription',
                loadComponent: () =>
                    import('./signup/signup.component').then((m) => m.SignupComponent),
            }, {
                path: '',
                redirectTo: '/auth/signin',
                pathMatch: 'full',
            },
        ],
    }, {
        path: '**',
        redirectTo: '/auth',
        pathMatch: 'full',
    },
];
