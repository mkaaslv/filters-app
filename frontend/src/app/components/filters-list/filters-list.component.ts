import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Filter } from '../../../types';
import { FiltersService } from '../../services/filters.service';
import { FilterDialogComponent } from '../filter-dialog/filter-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmPopupComponent } from '../confirm-popup/confirm-popup.component';
import { Subscription } from 'rxjs';
import { TimestampToDatePipe } from '../../pipes/timestamp-to-date.pipe';

@Component({
  selector: 'filters-list',
  standalone: true,
  templateUrl: './filters-list.component.html',
  styleUrl: './filters-list.component.css',
  imports: [
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    FilterDialogComponent,
    ConfirmPopupComponent,
    TimestampToDatePipe,
  ],
})
export class FiltersListComponent implements OnInit, OnDestroy {
  private reloadSubscription!: Subscription;

  displayedColumns = ['id', 'name', 'selection', 'modifiedDate', 'actions'];
  filters!: Filter[];
  baseUrl: string = 'http://localhost:8080/api/v1';

  constructor(
    private filtersService: FiltersService,
    private snackBar: MatSnackBar,
    private dialog: MatDialog
  ) {}

  ngOnInit() {
    // Get filters when the component is initialized
    this.fetchFilters();

    // Subscribe to reloadList observable to reload filters when needed
    this.reloadSubscription = this.filtersService.reloadList$.subscribe(() => {
      console.log('reload!');
      this.fetchFilters();
    });
  }

  ngOnDestroy(): void {
    this.reloadSubscription.unsubscribe();
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

  openConfirmPopup(filterId: number): void {
    const dialogRef = this.dialog.open(ConfirmPopupComponent, {
      width: '25em',
      height: '12em',
      data: {
        title: 'Confirm deletion',
        message: 'Are you sure you want to delete?',
      },
    });

    dialogRef.afterClosed().subscribe((confirmDelete) => {
      if (confirmDelete) {
        this.deleteRow(filterId);
      }
    });
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
