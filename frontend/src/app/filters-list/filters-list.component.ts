import { Component, OnInit } from '@angular/core';
import { Filter } from './filter';
import { MatTableModule } from '@angular/material/table';

@Component({
  selector: 'filters-list',
  standalone: true,
  imports: [MatTableModule],
  templateUrl: './filters-list.component.html',
  styleUrl: './filters-list.component.css'
})
export class FiltersListComponent implements OnInit {
  displayedColumns = ['id', 'name', 'selection', 'createdDate', 'modifiedDate'];
  dataSource = FILTERS;

  constructor() {}

  ngOnInit(): void {
  }
}

const FILTERS: Filter[] = [
  {
    id: 1,
    name: 'Filter 1',
    selection: 1,
    criterias: [
      {
        id: 1,
        filterId: 1,
        criteriaType: 1,
        operator: 'MORE_THAN',
        value: 10
      }
    ],
    createdDate: new Date().getDate().toString(),
    modifiedDate: null
  }
];
