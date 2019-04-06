import {Injectable} from '@angular/core';
import {IUser, Role} from '../shared/model/IUser';
import {Observable} from 'rxjs';
import * as jwt_decode from 'jwt-decode';

@Injectable()
export class CurrentUserService {
  getCurrentUser(): Observable<IUser> {
    return new Observable(subscriber => {

      const token = this.getToken();

      if (this.getToken() == null) {
        subscriber.next({
          role: Role.Anonymous,
          username: ''
        });
      } else {
        const decoded: IUser = jwt_decode(token);
        subscriber.next(decoded);
      }
    });
  }

  getToken(): string {
    let token = localStorage.getItem('token');
    if (!token) {
      token = null;
    }

    return token;
  }

  logout() {
    localStorage.removeItem('token');
  }

  private requestUserInfo() {
    // TODO: login request.
  }
}
