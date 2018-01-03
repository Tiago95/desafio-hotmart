import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AgmCoreModule } from '@agm/core';
import { MaterialModule} from '@angular/material';
import {TabsModule } from 'ngx-bootstrap';

import { NgxChartsModule} from '@swimlane/ngx-charts';

import { ContactsComponent } from './contacts.component';
import { ContactsRoutes } from './contacts.routing';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    MaterialModule,
    NgxChartsModule,
    TabsModule.forRoot(),
    AgmCoreModule.forRoot({apiKey: 'AIzaSyBtdO5k6CRntAMJCF-H5uZjTCoSGX95cdk'}),
    RouterModule.forChild(ContactsRoutes)
  ],
  declarations: [ 
    ContactsComponent,
  ]
})

export class ContactsModule {}
