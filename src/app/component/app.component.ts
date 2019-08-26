import { Component } from '@angular/core';
import { Filter } from '../domain/Filter';
import { CategoryService } from '../api/category.service';
import { Category } from '../domain/Category';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { SessionService } from '../api/session.service';
import { LocationService } from '../api/location.service';
import { CategoryDto } from '../api/model/category.dto';
import { SessionDto } from '../api/model/session.dto';
import { Session } from '../domain/Session';
import { Location } from '../domain/Location';
import { LocationDto } from '../api/model/location.dto';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  categoryList : Category[] = [];

  sessionList : Session[] = [];

  locationList : Location[] = [];

  constructor(
    private categoryService : CategoryService,
    private sessionService : SessionService,
    private locationService : LocationService) {}

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

  this.locationService.getLocationList().subscribe(
    (response : HttpResponse<LocationDto[]>) => {
      this.locationList = response.body.map(dto => new Location(dto));
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
