import {Component, OnInit} from '@angular/core';
import {ColorTheme, IColorThemeDefinition, RouteHelperService} from '../services/route-helper.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  theme: IColorThemeDefinition;
  ColorTheme = ColorTheme;

  constructor(private routeHelper: RouteHelperService) {

  }

  ngOnInit() {
    this.routeHelper.getRouteColor().subscribe(r => this.theme = r);
  }
}
