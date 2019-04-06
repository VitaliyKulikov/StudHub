import {NgModule} from '@angular/core';
import {ServerModule, ServerTransferStateModule} from '@angular/platform-server';
import {ModuleMapLoaderModule} from '@nguniversal/module-map-ngfactory-loader';

import {AppModule} from './app.module';
import {AppComponent} from './app.component';
import {SigninComponent} from './signin.component/signin/signin.component';
import {SignupComponent as OrganizationSignupComponent} from './organization/signup/signup.component';
import {SignupComponent as VolunteerSignupComponent} from './organization/signup/signup.component';
import {EventComponent} from './event/event.component';
import {EditEventComponent} from './event/edit-event/edit-event.component';
import {ViewEventComponent} from './event/view-event/view-event.component';
import {CreateEventComponent} from './event/create-event/create-event.component';
import {NotFoundPageComponent} from './not-found-page/not-found-page.component';
import {HeaderComponent} from './header/header.component';

@NgModule({
  imports: [
    // The AppServerModule should import your AppModule followed
    // by the ServerModule from @angular/platform-server.
    AppModule,
    ServerModule,
    ModuleMapLoaderModule,
    ServerTransferStateModule,
    OrganizationSignupComponent,
    VolunteerSignupComponent,
    SigninComponent,
    EventComponent,
    EditEventComponent,
    ViewEventComponent,
    NotFoundPageComponent,
    HeaderComponent,
    CreateEventComponent
  ],
  // Since the bootstrapped component is not inherited from your
  // imported AppModule, it needs to be repeated here.
  bootstrap: [AppComponent],
})
export class AppServerModule {}
