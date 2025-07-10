import { Routes } from '@angular/router';

export const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
{ path: 'home', loadComponent: () => import('./pages/home/home.page').then(m => m.HomePage) },

  // Public Routes
  { path: 'login', loadComponent: () => import('./pages/login/login.page').then(m => m.LoginPage) },
  { path: 'register', loadComponent: () => import('./pages/register/register.page').then(m => m.RegisterPage) },

  // Tabs Route (protected)
  {
    path: 'tabs',
    loadComponent: () => import('./components/tabs/tabs.component').then(m => m.TabsComponent),
    children: [
      { path: '', redirectTo: 'swipe', pathMatch: 'full' },
      { path: 'swipe', loadComponent: () => import('./pages/swipe/swipe.page').then(m => m.SwipePage) },
      { path: 'matches', loadComponent: () => import('./pages/matches/matches.page').then(m => m.MatchesPage) },
      { path: 'settings', loadComponent: () => import('./pages/settings/settings.page').then(m => m.SettingsPage) },
    ],
  },

  // Catch all
  { path: '**', redirectTo: 'login' },
];