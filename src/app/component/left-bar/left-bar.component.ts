import { Component, EventEmitter, Output, Input } from '@angular/core';
import {SelectItem} from 'primeng/api';
import { Filter } from 'src/app/domain/Filter';
import { Category } from 'src/app/domain/Category';
import { Location } from 'src/app/domain/Location';

@Component({
  selector: 'left-bar',
  templateUrl: './left-bar.component.html',
  styleUrls: ['./left-bar.component.scss']
})
export class LeftBarComponent {

  @Input()
  categoryList : Category[];

  @Input()
  locationList : Location[];

  selectedCategoryList: Category[] = [];

  selectedLocationList: Location[] = [];

  @Output() onFilterChangeEvent = new EventEmitter<Filter>();

  onFilterChange() {
    this.onFilterChangeEvent.emit(new Filter(this.selectedLocationList.map(selectItem => selectItem.label), this.selectedCategoryList.map(selectItem => selectItem.label)));
  }
}
