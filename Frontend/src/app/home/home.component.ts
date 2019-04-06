import {Component} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {CurrentUserService} from '../../services/current-user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {
  constructor(private http: HttpClient, private uservice: CurrentUserService) {
    // http.get('test').subscribe((d) => {
    //   console.log('wow');
    // });

    uservice.getCurrentUser().subscribe(u => console.log(u));
  }

  public message = `Angular Universal`;
  // tslint:disable-next-line:max-line-length
  public image = '../../assets/volunteer.jpg'
}
