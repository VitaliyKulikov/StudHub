import {Component, OnInit, ViewChild} from '@angular/core';
import {AuthService} from '../../../services/auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {
  @ViewChild('volunteerForm') volunteerForm;
  isLoading = false;

  constructor(private authService: AuthService) {
  }

  ngOnInit() {
  }

  async submit() {
    this.isLoading = true;
    try {
      await this.authService.signin(this.volunteerForm.value).toPromise();
    } finally {
      this.isLoading = false;
    }
  }
}
