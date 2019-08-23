import { Component, EventEmitter, Output, Input } from '@angular/core';
import {SelectItem} from 'primeng/api';
import { Filter } from 'src/app/domain/Filter';
import { Category } from 'src/app/domain/Category';

@Component({
  selector: 'left-bar',
  templateUrl: './left-bar.component.html',
  styleUrls: ['./left-bar.component.scss']
})
export class LeftBarComponent {

  @Input()
  categoryList : Category[];

  selectedCategoryList: Category[] = [];

  locationList: SelectItem[] = [
    {label:'Pathé 1', value:{id:1, name: 'Pathé 1', code: 'P1'}},
    {label:'Pathé 2', value:{id:1, name: 'Pathé 2', code: 'P2'}},
    {label:'Pathé 3', value:{id:1, name: 'Pathé 3', code: 'P3'}},
    {label:'Pathé 4', value:{id:1, name: 'Pathé 4', code: 'P4'}},
    {label:'Pathé 5', value:{id:1, name: 'Pathé 5', code: 'P5'}},
    {label:'Pathé 6', value:{id:1, name: 'Pathé 6', code: 'P6'}},
    {label:'Bonlieu Grande Salle', value:{id:1, name: 'Bonlieu Grande Salle', code: 'BGS'}},
    {label:'Bonlieu Petite Salle', value:{id:1, name: 'Bonlieu Petite Salle', code: 'BPS'}},
    {label:'La Turbine', value:{id:1, name: 'La turbine', code: 'TBN'}}
  ];

  selectedLocationList: SelectItem[] = [];

  @Output() onFilterChangeEvent = new EventEmitter<Filter>();

  onFilterChange() {
    this.onFilterChangeEvent.emit(new Filter(this.selectedLocationList.map(selectItem => selectItem.label), this.selectedCategoryList.map(selectItem => selectItem.label)));
  }
}
