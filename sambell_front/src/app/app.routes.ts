import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: '',
        redirectTo: 'home',
        pathMatch: 'full'
    }, {
        path: 'auth',
        title: 'Authentification',
        loadChildren: () => import('./auth/auth.routes').then((m) => m.routes),
    }, {
        path: 'products',
        title: 'Produits',
        loadComponent: () => import('./products/products.component').then((m) => m.ProductsComponent),
    }, {
        path: 'products',
        children: [
            {
                path: 'local',
                title: 'Produits | Local',
                loadComponent: () =>
                    import('./products/local/local.component').then((m) => m.LocalComponent),
            }, {
                path: 'international',
                title: 'Produits | International',
                loadComponent: () =>
                    import('./products/international/international.component').then((m) => m.InternationalComponent),
            },
        ],
    }, {
        path: 'shops',
        title: 'Boutiques',
        loadChildren: () => import('./shops/shops.routes').then((m) => m.routes),
    }, {
        path: 'shops',
        children: [
            {
                path: 'freelance',
                title: 'Boutiques | Freelance',
                loadComponent: () =>
                    import('./shops/freelance/freelance.component').then((m) => m.FreelanceComponent),
            }
        ]
    }, {
        path: 'home',
        title: 'Accueil',
        loadComponent: () => import('./home/home.component').then(m => m.HomeComponent)
    }, {
        path: 'map',
        loadComponent: () => import('./pages/map-common/map-common.component').then(m => m.MapCommonComponent)
    }, {
        path: 'not-found',
        title: 'Not Found',
        loadComponent: () => import('./pages/not-found/not-found.component').then(m => m.NotFoundComponent)
    }
];
