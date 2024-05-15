import { Component } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Filter } from '../../types';
import { FiltersService } from '../services/filters.service';
import { FilterDialogComponent } from '../components/filter-dialog/filter-dialog.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'filters-list',
  standalone: true,
  imports: [MatTableModule, MatButtonModule, MatIconModule, FilterDialogComponent],
  templateUrl: './filters-list.component.html',
  styleUrl: './filters-list.component.css',
})
export class FiltersListComponent {
  displayedColumns = [
    'id',
    'name',
    'selection',
    'modifiedDate',
    'actions',
  ];
  filters!: Filter[];
  baseUrl: string = 'http://localhost:8080/api/v1';

  constructor(
    private filtersService: FiltersService,
    private snackBar: MatSnackBar,
    private dialog: MatDialog
  ) {}

  ngOnInit() {
    this.fetchFilters();
  }

  fetchFilters() {
    this.filtersService
      .getFilters(`${this.baseUrl}/filters`)
      .subscribe((filters) => {
        this.filters = filters;
      });
  }

  openEditDialog(filterId: number) {
    console.log('filterId', filterId);
    this.dialog.open(FilterDialogComponent, { data: { filterId: filterId } });
  }

  deleteRow(filterId: number) {
    return this.filtersService
      .deleteFilter(`${this.baseUrl}/filters/${filterId}`)
      .subscribe(
        () => {
          // If deletion is successful
          this.snackBar.open('Filter deleted successfully', 'Close', {
            duration: 3000,
          });
          this.fetchFilters();
        },
        (error) => {
          // If deletion fails
          this.snackBar.open('Failed to delete filter', 'Close', {
            duration: 3000,
          });
          console.error('Failed to delete filter:', error);
        }
      );
  }
}
