import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {fadeInAnimation} from "../core/route-animation/route.animation";

@Component({
  selector: 'ms-contacts',
  templateUrl:'./contacts-component.html',
  styleUrls: ['./contacts-component.scss'],
  encapsulation: ViewEncapsulation.None,
    host: {
        "[@fadeInAnimation]": 'true'
    },
    animations: [ fadeInAnimation ]
})
export class ContactsComponent {

  
}