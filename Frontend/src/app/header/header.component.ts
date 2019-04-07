import {Component, OnDestroy, OnInit} from '@angular/core';
import {CurrentUserService} from '../../services/current-user.service';
import {IUser} from '../../shared/model/IUser';
import {ColorTheme, RouteHelperService} from '../../services/route-helper.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit, OnDestroy {
  user: IUser;

  constructor(private loggedUserService: CurrentUserService){
  }

  ngOnInit() {
    this.user = this.loggedUserService.getCurrentUserNoPromise();
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
