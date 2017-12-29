import { NgModule} from '@angular/core';
import { BrowserModule} from '@angular/platform-browser';
import { BrowserAnimationsModule} from "@angular/platform-browser/animations";
import { CommonModule } from '@angular/common';
import { HttpModule, Http } from '@angular/http';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { MaterialModule} from '@angular/material';
import { RouterModule } from '@angular/router';
import { FlexLayoutModule } from '@angular/flex-layout';
import { TourNgBootstrapModule } from 'ngx-tour-ng-bootstrap';

import { PerfectScrollbarModule } from 'ngx-perfect-scrollbar';
import { PERFECT_SCROLLBAR_CONFIG } from 'ngx-perfect-scrollbar';
import { PerfectScrollbarConfigInterface } from 'ngx-perfect-scrollbar';
import { TranslateModule, TranslateLoader, TranslateStaticLoader } from 'ng2-translate/ng2-translate';
import 'hammerjs';

import { DesafioHotmartAppComponent} from './app.component';
import { AppRoutes} from "./app-routing.module";
import { AuthLayoutComponent } from './auth/auth-layout.component';



export function createTranslateLoader(http: Http) {
  return new TranslateStaticLoader(http, 'assets/i18n', '.json');
}

const DEFAULT_PERFECT_SCROLLBAR_CONFIG: PerfectScrollbarConfigInterface = {
  suppressScrollX: true
};

@NgModule({
	imports: [
		BrowserModule,
		BrowserAnimationsModule,
		FormsModule,
		ReactiveFormsModule,
		FlexLayoutModule,
		MaterialModule,
		RouterModule.forRoot(AppRoutes),
      PerfectScrollbarModule,
      HttpModule,
      TourNgBootstrapModule.forRoot(),
      TranslateModule.forRoot({
		   provide: TranslateLoader,
		   useFactory: (createTranslateLoader),
		   deps: [Http]
		}),
	],
	declarations: [
		DesafioHotmartAppComponent,
		AuthLayoutComponent
	],
	bootstrap: [DesafioHotmartAppComponent],
	providers:[
      {
         provide: PERFECT_SCROLLBAR_CONFIG,
         useValue: DEFAULT_PERFECT_SCROLLBAR_CONFIG
      }
	]
})
export class DesafioHotmartAppModule { }
