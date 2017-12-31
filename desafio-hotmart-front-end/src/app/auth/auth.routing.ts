import { Routes } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { RegisterComponent } from 'app/auth/register/register.component';

export const AuthRoutes: Routes = [{
  path: '',
  redirectTo: 'login',
  pathMatch: 'full',
},{
  path: '',
  children: [{
    path: 'login',
    component: LoginComponent
  }, {
    path: 'register',
    component: RegisterComponent
  }]
}];
