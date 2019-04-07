import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/internal/operators';
import {Observable} from 'rxjs';
import {IEvent} from '../shared/model/IEvent';

@Injectable()
export class EventService {
  constructor(private http: HttpClient) {

  }

  get(): Observable<IEvent[]> {
    return this.http.get('api/events').pipe(map(resp => {
      return resp['_embedded'].eventDtoes;
    }));
  }
}
