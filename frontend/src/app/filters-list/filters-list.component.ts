import { Component } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { Filter } from '../../types';
import { FiltersService } from '../services/filters.service';

@Component({
  selector: 'filters-list',
  standalone: true,
  imports: [MatTableModule],
  templateUrl: './filters-list.component.html',
  styleUrl: './filters-list.component.css',
})
export class FiltersListComponent {
  displayedColumns = ['id', 'name', 'selection', 'createdDate', 'modifiedDate'];
  dataSource = FILTERS;

  constructor(private filtersService: FiltersService) {}

  ngOnInit() {
    this.filtersService
      .getFilters('http://localhost:8080/api/v1/filters')
      .subscribe((filters) => {
        console.log(filters);
      });
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
        value: 10,
      },
    ],
    createdDate: new Date().getDate().toString(),
    modifiedDate: null,
  },
];
