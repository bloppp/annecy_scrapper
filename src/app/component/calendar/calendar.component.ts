import { Component, Input } from '@angular/core';
import {CardModule} from 'primeng/card';
import {Session} from '../../domain/Session'
import {ButtonModule} from 'primeng/button';
import { Movie } from 'src/app/domain/Movie';
import { Category } from 'src/app/domain/Category';
import { Filter } from 'src/app/domain/Filter';

@Component({
  selector: 'calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.scss']
})
export class CalendarComponent {

  @Input()
  set filter(filter: Filter) {
      this.sessionList = this.sessionList.map(session => {
        if ((filter.category.length ===0 || filter.category.includes(session.movie.category.id)) && (filter.location.length === 0 || filter.location.includes(session.location.name))) {
          session.show();
        } else {
          session.hide();
        }
        return session;
      });
  }

  @Input()
  sessionList : Session[] = [];
}
