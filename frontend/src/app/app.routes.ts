import { Routes } from '@angular/router';

export const routes: Routes = [
	{
		path: 'home',
		loadComponent: () =>
			import('./pages/home/home.page').then((m) => m.HomePage),
	},
	{
		path: '',
		redirectTo: 'home',
		pathMatch: 'full',
	},
  {
    path: 'login',
    loadComponent: () => import('./pages/login/login.page').then( m => m.LoginPage)
  },
  {
    path: 'register',
    loadComponent: () => import('./pages/register/register.page').then( m => m.RegisterPage)
  },
  {
    path: 'swipe',
    loadComponent: () => import('./pages/swipe/swipe.page').then( m => m.SwipePage)
  },
  {
    path: 'matches',
    loadComponent: () => import('./pages/matches/matches.page').then( m => m.MatchesPage)
  },
];
