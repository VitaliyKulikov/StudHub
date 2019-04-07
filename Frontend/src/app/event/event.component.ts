import {Component, OnInit} from '@angular/core';
import {EventService} from '../../services/event.service';
import {IEvent} from '../../shared/model/IEvent';

@Component({
  selector: 'app-events',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.scss']
})
export class EventComponent implements OnInit {
  events: IEvent[] = [];
  myEvents: IEvent[] = [];
  rawEvents: IEvent[] = [];
  searchTerm = '';
  isLoading = false;
  isShowMyLimited = true;
  isShowAllLimited = true;

  constructor(private eventService: EventService) {
  }

  async init() {
    this.isLoading = true;
    try {
      this.rawEvents = await this.eventService.get().toPromise();
      this.filter();
    } finally {
      this.isLoading = false;
    }
  }

  ngOnInit() {
    this.init();
  }

  limitIfNecessary(events: IEvent[], isLimited: boolean) {
    if (isLimited) {
      return events.slice(0, 4);
    }

    return events;
  }

  toggleMyLimited() {
    this.isShowMyLimited = !this.isShowMyLimited;
  }

  toggleAllLimited() {
    this.isShowAllLimited = !this.isShowAllLimited;
  }

  filter() {
    if (!this.searchTerm) {
      this.events = this.rawEvents;
      this.myEvents = this.myEvents;
    } else {
      this.events = this.events.filter(e => e.name.indexOf(this.searchTerm) !== -1);
      this.myEvents = this.events.filter(e => e.name.indexOf(this.searchTerm) !== -1);
    }
  }
}
