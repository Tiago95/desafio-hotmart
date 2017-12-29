import {Component, Optional, ViewEncapsulation} from '@angular/core';
import {TranslateService} from 'ng2-translate/ng2-translate';
import {MdDialog, MdDialogConfig, MdDialogRef, MdSnackBar} from '@angular/material';

@Component({
  	selector: 'desafio-hotmart-app',
  	template:'<router-outlet></router-outlet>',
    encapsulation: ViewEncapsulation.None
})
export class DesafioHotmartAppComponent {
   constructor(translate: TranslateService) {
       translate.addLangs(['en', 'fr']);
       translate.setDefaultLang('en');

       const browserLang: string = translate.getBrowserLang();
       translate.use(browserLang.match(/en|fr/) ? browserLang : 'en');
     }
}
