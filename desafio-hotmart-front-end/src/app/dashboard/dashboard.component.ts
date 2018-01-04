import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {fadeInAnimation} from "../core/route-animation/route.animation";
import {Router} from "@angular/router";

@Component({
  selector: 'ms-dashboard',
  templateUrl:'./dashboard-component.html',
  styleUrls: ['./dashboard-component.scss'],
  encapsulation: ViewEncapsulation.None,
    host: {
        "[@fadeInAnimation]": 'true'
    },
    animations: [ fadeInAnimation ]
})
export class DashboardComponent {

    public constructor(private router: Router){}

    public goToContacts(){

        this.router.navigate(["/contacts"]);

    }

    public goToConversas(){

        this.router.navigate(["/chat"]);
        
    }

}