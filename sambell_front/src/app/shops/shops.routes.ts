import { Routes } from '@angular/router';
import { ShopsComponent } from './shops.component';

export const routes: Routes = [
    {
        path: '',
        component: ShopsComponent,
        children: [
            {
                path: 'freelance',
                loadComponent: () =>
                    import('./freelance/freelance.component').then((m) => m.FreelanceComponent),
            }, {
                path: '',
                redirectTo: '/shops/freelance',
                pathMatch: 'full',
            },
        ],
    }, {
        path: '**',
        redirectTo: '/shops',
        pathMatch: 'full',
    },
];
