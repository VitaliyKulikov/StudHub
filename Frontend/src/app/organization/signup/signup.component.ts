import {Component, OnInit, ViewChild} from '@angular/core';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {
  @ViewChild('volunteerForm') volunteerForm;
  model: any = {};

  constructor() {
  }

  ngOnInit() {
  }

  submit() {
    console.log(this.volunteerForm.value);
  }
}
