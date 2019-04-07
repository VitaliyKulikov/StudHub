import {Inject, Injectable, PLATFORM_ID} from '@angular/core';
import {IUser, IUserToken, Role} from '../shared/model/IUser';
import {Observable, Subject} from 'rxjs';
import * as jwt_decode from 'jwt-decode';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {tap} from 'rxjs/internal/operators/tap';
import {isPlatformBrowser} from '@angular/common';

@Injectable()
export class CurrentUserService {
  private onUserChangeSubs: ((IUser) => any)[] = [];

  constructor(private http: HttpClient,
              private router: Router,
              @Inject(PLATFORM_ID) private platformId: Object) {

  }

  getCurrentUserNoPromise(): IUser {
    const token = this.getToken();

    if (this.getToken() == null) {
      return {
        role: Role.Anonymous,
        username: ''
      };
    } else {
      const decoded: IUserToken = jwt_decode(token);
      if (new Date().getTime() - decoded.exp * 1000 <= 0) {
        return {
          username: decoded.iss,
          role: decoded.role
        };
      } else {
        this.logout();
        return {
          role: Role.Anonymous,
          username: ''
        };
      }
    }
  }

  getCurrentUser(): Observable<IUser> {
    return new Observable<IUser>(subscriber => {

      const token = this.getToken();

      if (this.getToken() == null) {
        subscriber.next({
          role: Role.Anonymous,
          username: ''
        });
      } else {
        const decoded: IUserToken = jwt_decode(token);
        if (new Date().getTime() - decoded.exp * 1000 <= 0) {
          subscriber.next({
            username: decoded.iss,
            role: decoded.role
          });
        } else {
          this.logout();
          subscriber.complete();
        }
      }
    });
  }

  getToken(): string {
    let token;
    if (isPlatformBrowser(this.platformId)) {
      token = localStorage.getItem('token');
    }
    if (!token) {
      token = null;
    }

    return token;
  }

  logout() {
    if (isPlatformBrowser(this.platformId)) {
      localStorage.removeItem('token');
    }
    this.notifyUserChange();
  }

  signin(credentials): Observable<any> {
    return this.http.post<string>('api/token', credentials).pipe(tap(t => {
      if (isPlatformBrowser(this.platformId)) {
        localStorage.setItem('token', t);
      }
      this.notifyUserChange();
    }));
  }

  setOnUserChange(fn: (IUser) => any) {
    this.onUserChangeSubs.push(fn);
  }

  unsubscribeAll() {
    // temporary solution.
    this.onUserChangeSubs = [];
  }

  private notifyUserChange() {
    this.onUserChangeSubs.forEach(u => u(this.getCurrentUserNoPromise()));
  }
}
