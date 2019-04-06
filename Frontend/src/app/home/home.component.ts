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
    http.get('test').subscribe((d) => {
      console.log('wow');
    });

    uservice.getCurrentUser().subscribe(u => console.log(u));
  }

  public message = `Angular Universal`;
  // tslint:disable-next-line:max-line-length
  public image = 'https://camo.githubusercontent.com/81f72f2fdf98aa1d30b5b215bc8ca9420b249e81/68747470733a2f2f616e67756c61722e696f2f67656e6572617465642f696d616765732f6d61726b6574696e672f636f6e636570742d69636f6e732f756e6976657273616c2e706e67';
}