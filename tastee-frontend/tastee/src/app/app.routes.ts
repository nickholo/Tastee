// app.routes.ts
import { Routes } from '@angular/router';
import { authGuard } from './guards/auth-guard';
import { environment } from 'src/environments/environment';
export const routes: Routes = [
  {
    path: 'login',
    loadComponent: () => import('./pages/login/login.page').then(m => m.LoginPage)
  },
  {
    path: 'app',                      // renamed from '' for clarity
    canActivate: [authGuard],         // protects everything inside
    loadChildren: () => import('./tabs/tabs.routes').then(m => m.routes)
  },
  {
    path: '',
    redirectTo: 'app',               // / → /app → /app/tabs/tab1
    pathMatch: 'full'
  },
  {
    path: '**',                                              // catch all unknown routes
    redirectTo: environment.production ? 'login' : 'app'   // redirect based on environment
  },
  {
    path: 'profile',
    loadComponent: () => import('./pages/profile/profile.page').then( m => m.ProfilePage)
  }
];