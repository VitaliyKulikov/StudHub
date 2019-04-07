import {Component, Input, OnInit} from '@angular/core';
import {IEvent} from '../../../shared/model/IEvent';

@Component({
  selector: 'app-item-event',
  templateUrl: './item-event.component.html',
  styleUrls: ['./item-event.component.scss']
})
export class ItemEventComponent implements OnInit {
  @Input() event: IEvent;

  constructor() {
  }

  ngOnInit() {
  }

}
