import { NgModule } from '@angular/core';
import { Routes } from '@angular/router';

import { AuthLayoutComponent } from './auth/auth-layout.component';
import { MainComponent } from 'app/main/main.component';

export const AppRoutes: Routes = [{
  path: '',
  redirectTo: 'authentication/login',
  pathMatch: 'full',
}, {
  path: '',
  component: AuthLayoutComponent,
  children: [{
    path: 'authentication',
    loadChildren: './auth/auth.module#AuthModule'
  }]
},{
  path: '',
  component: MainComponent,
  children: [{
    path: 'dashboard',
    loadChildren: './dashboard/dashboard.module#DashboardModule'
  },
  {
    path: 'contacts',
    loadChildren: './contacts/contacts.module#ContactsModule'
  },{
    path: 'chat',
    loadChildren: './chat/chat.module#ChatModule'
  }]
}];

