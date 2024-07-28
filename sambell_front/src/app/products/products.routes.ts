import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        children: [
            {
                path: 'local',
                loadComponent: () =>
                    import('./local/local.component').then((m) => m.LocalComponent),
            }, {
                path: 'international',
                loadComponent: () =>
                    import('./international/international.component').then((m) => m.InternationalComponent),
            },
        ],
    },
];
