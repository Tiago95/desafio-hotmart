import { Routes } from '@angular/router';

import { ContactsComponent } from './contacts.component';

export const ContactsRoutes: Routes = [{
  path: '',
  children: [{
    path: '',
    component: ContactsComponent
  }]
}];
