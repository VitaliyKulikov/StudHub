import {Injectable} from '@angular/core';
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {tap, catchError} from 'rxjs/operators/';
import {ErrorSerivce} from '../services/error.service';
import {IErrorResponce} from '../shared/model/responces/IErrorResponce';

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

        this.errorService.pushError({
            status: res.status,
            title: `${res.statusText}: ${res.message}`,
            description: res.error
        });
    }
}

