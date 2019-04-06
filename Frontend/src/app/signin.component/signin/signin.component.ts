import {Component, OnInit, ViewChild} from '@angular/core';
import {CurrentUserService} from '../../../services/current-user.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.scss']
})
export class SigninComponent implements OnInit {
  @ViewChild('loginForm') loginForm;
  isLoading = false;

  constructor(private currentUserService: CurrentUserService,
              private router: Router) {
  }

  ngOnInit() {
  }

  async submit() {
    this.isLoading = true;
    try {
      await this.currentUserService.signin(this.loginForm.value).toPromise();
      this.router.navigate(['home']);
    } finally {
      this.isLoading = false;
    }
  }
}
