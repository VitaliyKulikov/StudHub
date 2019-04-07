import {Injectable} from '@angular/core';
import {NavigationEnd, Router} from '@angular/router';
import {Observable} from 'rxjs';
import {filter, map, shareReplay} from 'rxjs/internal/operators';

const blackRoutes = [
];

const blackHeaderRoute = [
];


@Injectable()
export class RouteHelperService {
  constructor(private router: Router) {
  }

  getRouteColor(): Observable<IColorThemeDefinition> {
    const stream = this.router.events
      .pipe(
        filter(e => e instanceof NavigationEnd),
        shareReplay(1),
        map((e: NavigationEnd) => {
          if (blackRoutes.indexOf(e.urlAfterRedirects) !== -1) {
            return {
              color: ColorTheme.Black,
              isHeaderOnly: false
            };
          } else {
            if (blackHeaderRoute.indexOf(e.urlAfterRedirects) !== -1) {
              return {
                color: ColorTheme.Black,
                isHeaderOnly: true
              };
            }

            return {
              color: ColorTheme.White,
              isHeaderOnly: true
            };
          }
        }));

    stream.subscribe(s => {
      /*ref count use instead is preferable*/
    });
    return stream;
  }
}

export interface IColorThemeDefinition {
  color: ColorTheme;
  isHeaderOnly: boolean;
}

export enum ColorTheme {
  White,
  Black
}
