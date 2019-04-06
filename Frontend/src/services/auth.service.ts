import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {CurrentUserService} from './current-user.service';

@Injectable()
export class AuthService {
  constructor(private http: HttpClient, private currentUserService: CurrentUserService) {
  }

  signin() {

  }
}
