import {Injectable} from '@angular/core';
import {Observable, Subject} from 'rxjs';
import {IError} from '../shared/model/IError';
import {ToastrService} from 'ngx-toastr';

@Injectable()
export class ErrorSerivce {
    private errorEmiter = new Subject<IError>();

    constructor(private toastr: ToastrService) {
    }

    pushError(err: IError) {
        this.errorEmiter.next(err);
        console.log(err);
        this.toastr.error(err.description, err.title);
    }

    getErrorStream(): Observable<IError> {
        return this.errorEmiter.asObservable();
    }
}
