import { NgModule } from '@angular/core';
import { Routes } from '@angular/router';

import { AuthLayoutComponent } from './auth/auth-layout.component';

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
}];

