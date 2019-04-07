import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';

import {AppComponent} from './app.component';
import {HomeComponent} from './home/home.component';
import {TransferHttpCacheModule} from '@nguniversal/common';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {CommonModule} from '@angular/common';
import {ToastrModule} from 'ngx-toastr';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {ErrorInterceptor} from '../interceptors/error.interceptor';
import {ErrorSerivce} from '../services/error.service';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HeaderComponent} from './header/header.component';
import {SigninComponent} from './signin.component/signin/signin.component';
import {EventComponent} from './event/event.component'
import {EditEventComponent} from './event/edit-event/edit-event.component'
import {ItemEventComponent} from './event/item-event/item-event.component'
import {ViewEventComponent} from './event/view-event/view-event.component'
import {CreateEventComponent} from './event/create-event/create-event.component'
import {SignupComponent as OrganizationSignupComponent} from './organization/signup/signup.component';
import {SignupComponent as VolunteerSignupComponent} from './volunteer/signup/signup.component';
import {NotFoundPageComponent} from './not-found-page/not-found-page.component';

import {TokenInterceptor} from '../interceptors/token.interceptor';
import {CurrentUserService} from '../services/current-user.service';
import {LocalStorage, LocalStorageC} from './storage';
import {AuthService} from '../services/auth.service';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    OrganizationSignupComponent,
    VolunteerSignupComponent,
    SigninComponent,
    EventComponent,
    ViewEventComponent,
    EditEventComponent,
    NotFoundPageComponent,
    HeaderComponent,
    CreateEventComponent,
    ItemEventComponent,
  ],
  imports: [
    BrowserModule.withServerTransition({appId: 'my-app'}),
    RouterModule.forRoot([
      { path: '', component: HomeComponent, pathMatch: 'full'},
      { path: 'events', children: [
        { path: '', component:  EventComponent },
        { path: 'create', component:  CreateEventComponent },
        { path: ':id', component:  ViewEventComponent },
        { path: ':id/edit', component:  EditEventComponent },
      ]},
      { path: 'signin', component: SigninComponent, pathMatch: 'full' },
      { path: 'organization', children: [
        { path: '', redirectTo: '/', pathMatch: 'full' },
        { path: 'signup', component:  OrganizationSignupComponent },
        { path: ':id', component:  OrganizationSignupComponent },
      ]},
      { path: 'volunteer', children: [
        { path: '', redirectTo: '/', pathMatch: 'full' },
        { path: 'signup', component:  VolunteerSignupComponent },
        { path: ':id', component:  VolunteerSignupComponent },
      ]},
      {path: '**', component: NotFoundPageComponent}
      // { path: 'lazy', loadChildren: './lazy/lazy.module#LazyModule'},
      // { path: 'lazy/nested', loadChildren: './lazy/lazy.module#LazyModule'},
    ]),
    TransferHttpCacheModule,
    CommonModule,
    HttpClientModule,
    BrowserAnimationsModule, // required animations module
    ToastrModule.forRoot(),
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    ErrorSerivce,
    AuthService,
    CurrentUserService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ErrorInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
