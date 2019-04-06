import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';

import {AppComponent} from './app.component';
import {HomeComponent} from './home/home.component';
import {TransferHttpCacheModule} from '@nguniversal/common';
import {SignupComponent} from './signup.component/signup/signup.component';
import {SigninComponent} from './signin.component/signin/signin.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SignupComponent,
    SigninComponent,
  ],
  imports: [
    BrowserModule.withServerTransition({appId: 'my-app'}),
    RouterModule.forRoot([
      { path: '', component: HomeComponent, pathMatch: 'full'},
      { path: 'lazy', loadChildren: './lazy/lazy.module#LazyModule'},
      { path: 'lazy/nested', loadChildren: './lazy/lazy.module#LazyModule'},
      { path: 'signin', component: SigninComponent, pathMatch: 'full' },
      { path: 'organization', children: [
        { path: '', redirectTo: '/', pathMatch: 'full' },
        { path: 'signup', component:  SignupComponent },
        { path: ':id', component:  SignupComponent },
      ]},
      { path: 'volunteer', children: [
        { path: '', redirectTo: '/', pathMatch: 'full' },
        { path: 'signup', component:  SignupComponent },
        { path: ':id', component:  SignupComponent },
      ]},
    ]),
    TransferHttpCacheModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
