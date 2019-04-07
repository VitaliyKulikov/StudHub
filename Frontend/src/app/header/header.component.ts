import {Component, OnDestroy, OnInit} from '@angular/core';
import {CurrentUserService} from '../../services/current-user.service';
import {IUser} from '../../shared/model/IUser';
import {initDomAdapter} from '@angular/platform-browser/src/browser';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit, OnDestroy {
  user: IUser;

  constructor(private loggedUserService: CurrentUserService) {

  }

  ngOnInit() {
    this.user = this.loggedUserService.getCurrentUserNoPromise();
    console.group('init header');
    console.log(this.user);
    console.groupEnd();
    this.loggedUserService.setOnUserChange(this.handleUserChange.bind(this));
  }

  ngOnDestroy(): void {
    this.loggedUserService.unsubscribeAll();
  }

  handleUserChange(user) {
    this.user = user;
    console.log(user);
  }
}
