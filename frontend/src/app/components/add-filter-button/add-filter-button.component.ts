import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog } from '@angular/material/dialog';
import { FilterDialogComponent } from '../filter-dialog/filter-dialog.component';

@Component({
  selector: 'app-add-filter-button',
  standalone: true,
  imports: [FilterDialogComponent, MatButtonModule],
  templateUrl: './add-filter-button.component.html',
  styleUrl: './add-filter-button.component.css',
})
export class AddFilterButtonComponent {
  constructor(private dialog: MatDialog) { }

  openFilterDialog(): void {
    this.dialog.open(FilterDialogComponent);
  }
}
