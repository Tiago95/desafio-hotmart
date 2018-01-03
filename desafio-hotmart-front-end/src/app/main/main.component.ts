import { AuthenticationService } from './../services/authentication.service';
import { Usuario } from './../models/usuario';
import { Component, OnInit, OnDestroy, ViewChild, HostListener, ViewEncapsulation } from '@angular/core';
import { MenuItems } from '../core/menu/menu-items/menu-items';
import { PageTitleService } from '../core/page-title/page-title.service';
import { TranslateService } from 'ng2-translate/ng2-translate';
import { Router, NavigationEnd } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import {MediaChange, ObservableMedia} from "@angular/flex-layout";
import { PerfectScrollbarConfigInterface,
PerfectScrollbarComponent, PerfectScrollbarDirective } from 'ngx-perfect-scrollbar';
import PerfectScrollbar from 'perfect-scrollbar';
import { AppUtils } from 'app/utils/app-utils';
const screenfull = require('screenfull');

@Component({
    selector: 'silk-layout',
  	templateUrl:'./main-material.html',
  	styleUrls: ['./main-material.scss'],
    encapsulation: ViewEncapsulation.None
})
export class MainComponent{
    
    private isFullscreen: boolean = false;    
    private sidenavOpen: boolean = true;
    private sidenavMode: string = 'side';
    private themeHeaderSkinColor: any = "header-default";
    private themeSidebarSkinColor: any = "sidebar-default";
    private currentUser: Usuario;
    private randomUserNumber: number = AppUtils.getRandomInt(1, 13);
    
    @ViewChild('sidenav') sidenav;
	public constructor(private menuItems: MenuItems, private authService: AuthenticationService, private router: Router) {

        this.currentUser = JSON.parse(localStorage.getItem("currentUser")) as Usuario;

    }

	public toggleFullscreen() {
    	if (screenfull.enabled) {
    		screenfull.toggle();
      		this.isFullscreen = !this.isFullscreen;
    	}
      }
      
    public onActivate(e, scrollContainer) {
        scrollContainer.scrollTop = 0;
    }

    public logout(){

        this.authService.logOut().then(e => this.router.navigate(['/authentication/login']));

    }

}


