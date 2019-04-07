import {Component, OnInit, ViewChild} from '@angular/core';
import {AuthService} from '../../../services/auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {
  @ViewChild('volunteerForm') volunteerForm;
  isLoading = false;

  constructor(private authService: AuthService, private router: Router) {
  }

  ngOnInit() {
  }

  async submit() {
    this.isLoading = true;
    try {
      await this.authService.signupVolunteer(this.volunteerForm.value).toPromise();
      this.router.navigate(['signin']);
    } finally {
      this.isLoading = false;
    }
  }
}
