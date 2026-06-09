import { Routes } from '@angular/router';
import { TabsPage } from './tabs.page';
export const routes: Routes = [
  {
    path: 'tabs',
    component: TabsPage,
    children: [
      {
        // TODO Fix active Icon 
        path: 'home',
        loadComponent: () => import('../pages/tab1/tab1.page').then(m => m.Tab1Page)
      },
      {
        path: 'tab2',
        loadComponent: () => import('../pages/tab2/tab2.page').then(m => m.Tab2Page)
      },
      {
        path: 'profile',
        loadComponent: () => import('../pages/profile/profile.page').then(m => m.ProfilePage)
      },
      {
        path: '',
        redirectTo: 'home',   // relative redirect — no leading slash
        pathMatch: 'full'
      }
    ]
  },
  {
    path: '',
    redirectTo: 'tabs',       // app/ → app/tabs → app/tabs/tab1
    pathMatch: 'full'
  }
];