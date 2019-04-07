import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class AuthService {
  constructor(private http: HttpClient) {
  }

  signupVolunteer(req) {
    return this.http.post('api/user-signup', req);
  }

  signupOrganisation(req) {
    return this.http.post('api/organisation-signup', req);
  }
}
