import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MdIconModule} from '@angular/material';

import { LoginComponent } from './login/login.component';
import { AuthRoutes } from './auth.routing';



@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    MdIconModule,
    RouterModule.forChild(AuthRoutes)
  ],
  declarations: [ 
    LoginComponent,
  ]
})

export class AuthModule {}
