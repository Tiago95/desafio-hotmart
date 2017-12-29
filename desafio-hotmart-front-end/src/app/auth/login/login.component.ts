import { Component, OnInit ,ViewEncapsulation } from '@angular/core';
import {Router} from "@angular/router";

@Component({
   selector: 'ms-login-session',
   templateUrl:'./login-component.html',
   styleUrls: ['./login-component.scss'],
   encapsulation: ViewEncapsulation.None,
})
export class LoginComponent {
	
  email: string;
  password: string;

  constructor(
    private router: Router
  ) { }

  login() {
    this.router.navigate(['/']);
  }
	
}



