import { Routes } from '@angular/router';

import { ContactsComponent } from './contacts.component';

export const ContactsRoutes: Routes = [{
  path: '/dashboard/contacts',
  children: [{
    path: 'contacts',
    component: ContactsComponent
  }]
}];
