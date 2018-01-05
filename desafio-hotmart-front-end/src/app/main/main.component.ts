import { AppUtils } from './../utils/app-utils';
import { LastChatInfo } from './../models/last-chat-info';
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
import { ChatService } from 'app/services/chat.service';
import { AlertCenterService } from 'ng2-alert-center'
import { Alert } from 'ng2-alert-center/alert-center/model/alert';
import { AlertType } from 'ng2-alert-center/alert-center/model/alert-type';

const screenfull = require('screenfull');

@Component({
    selector: 'silk-layout',
  	templateUrl:'./main-material.html',
  	styleUrls: ['./main-material.scss'],
    encapsulation: ViewEncapsulation.None
})
export class MainComponent implements OnInit{
    
    private isFullscreen: boolean = false;    
    private sidenavOpen: boolean = true;
    private sidenavMode: string = 'side';
    private themeHeaderSkinColor: any = "header-default";
    private themeSidebarSkinColor: any = "sidebar-default";
    private currentUser: Usuario;
    private randomUserNumber: number = AppUtils.getRandomInt(1, 13);
    private lastChatInfo: LastChatInfo;
    
    @ViewChild('sidenav') sidenav;
	public constructor(private menuItems: MenuItems, private authService: AuthenticationService, private router: Router, private chatService: ChatService, private alertService: AlertCenterService) {

        this.currentUser = JSON.parse(localStorage.getItem("currentUser")) as Usuario;

    }

    public ngOnInit(){

        this.chatService.getMensagensLidasAtualizadas().subscribe(ret => this.atualizarLastChatInfo());
        this.chatService.getNewMessage().subscribe(chatMessage => this.atualizarLastChatInfo());

        this.atualizarLastChatInfo();

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

    private exibirModalMensagensNaoLidas(){

        if(this.lastChatInfo && this.lastChatInfo.quantidadeMensagensNaoLidas
            && this.lastChatInfo.quantidadeMensagensNaoLidas > 0){

            let msg: string = this.currentUser.nick + ' você tem ' + this.lastChatInfo.quantidadeMensagensNaoLidas + ' mensagens não lidas. Vá para o chat/histórico de mensagens para descobrir as mensagens. :-)!!'

            this.alertService.alert(Alert.create(AlertType.INFO, msg, 20000));

        }       

    }

    private atualizarLastChatInfo(){

        this.chatService.getLastChatInfo().then(lastChatInfo => {

            this.lastChatInfo = lastChatInfo;

            if(this.lastChatInfo && this.lastChatInfo.ultimasMensagensRecebidas){

                let mapaAvatar: Map<number, number> = new Map();

                this.lastChatInfo.ultimasMensagensRecebidas.map(chatMessage => {

                    if(!mapaAvatar.has(chatMessage.idUsuarioOrigem)){

                        mapaAvatar.set(chatMessage.idUsuarioOrigem, AppUtils.getRandomInt(1, 13));

                    }

                    return chatMessage;

                });

                this.lastChatInfo.ultimasMensagensRecebidas.map(chatMessage => {

                    chatMessage['avatar'] = mapaAvatar.get(chatMessage.idUsuarioOrigem);

                    return chatMessage;

                });

                this.exibirModalMensagensNaoLidas();

            }

        });

    }

}


