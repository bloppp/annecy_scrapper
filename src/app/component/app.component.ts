import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Filter } from '../domain/Filter';
import { CategoryService } from '../api/category.service';
import { Category } from '../domain/Category';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { SessionService } from '../api/session.service';
import { CategoryDto } from '../api/model/categoryDto';
import { SessionDto } from '../api/model/sessionDto';
import { Session } from '../domain/Session';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  categoryList : Category[] = [];

  sessionList : Session[] = [];

  constructor(private categoryService : CategoryService, private sessionService : SessionService) {}

ngOnInit() {
  this.categoryService.getCategoryList().subscribe(
    (response : HttpResponse<CategoryDto[]>) => {
      this.categoryList = response.body.map(dto => new Category(dto));
    },
    (error : HttpErrorResponse) => {
      console.error("Error :" + error.message);
    }
  );
  this.sessionService.getAllSession().subscribe(
    (response : HttpResponse<SessionDto[]>) => {
      this.sessionList = response.body.map(dto => new Session(dto));
    },
    (error : HttpErrorResponse) => {
      console.error("Error :" + error.message);
    }
  );
}

  filter : Filter = new Filter([], []);

  title = 'annecy-scraper';



  handleFilterChange(filter : Filter) {
    this.filter = filter;
  }
}
