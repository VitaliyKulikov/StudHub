import {Injectable} from '@angular/core';
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {tap, catchError} from 'rxjs/operators/';
import {ErrorSerivce} from '../services/error.service';

let fieldErrorMap: any;

fieldErrorMap = {
  'firstName': 'Ім\'я',
  'lastName': 'Прізвище',
  'patronymic': 'По-батькові',

  get(name) {
    if (Object.keys(fieldErrorMap).indexOf(name) !== -1) {
      return fieldErrorMap[name];
    }

    return name;
  }
};

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
  constructor(private errorService: ErrorSerivce) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(catchError((err, caught) => {
      if (err instanceof HttpErrorResponse && this.shouldBeIntercepted(err)) {
        this.handleErrorResponce(err);
      }
      throw err;
    }));
  }

  shouldBeIntercepted(event: HttpErrorResponse) {
    return event.status >= 400;
  }

  handleErrorResponce(res: HttpErrorResponse) {
    const data = res.error;
    let description = data.toString();

    if (data.errors) {
      const errors: any[] = data.errors;

      errors.forEach(e => {
        this.errorService.pushError({
          title: fieldErrorMap.get(e.field),
          description: `${fieldErrorMap.get(e.field)} ${e.defaultMessage}`
        });
      });

      description = errors.map(e => `${fieldErrorMap.get(e.field)} ${e.defaultMessage}`).join('; ');
    } else {
      this.errorService.pushError({
        status: res.status,
        title: `${res.status}`,
        description: description
      });
    }
  }
}

