import {Component, OnInit, ViewChild} from '@angular/core';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.scss']
})
export class SigninComponent implements OnInit {
  @ViewChild('loginForm') loginForm;
  constructor() { }

  ngOnInit() {
  }

  submit() {
    console.log(JSON.stringify(this.loginForm.value, null, 2));
  }
}
