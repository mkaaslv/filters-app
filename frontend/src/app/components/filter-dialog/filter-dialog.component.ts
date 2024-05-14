import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormField } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatRadioButton } from '@angular/material/radio';
import { FilterEditorComponent } from '../filter-editor/filter-editor.component';

@Component({
  selector: 'app-filter-dialog',
  standalone: true,
  imports: [
    FilterEditorComponent,
    MatIconModule,
    MatFormField,
    MatRadioButton,
    MatDialogModule,
    MatButtonModule,
  ],
  templateUrl: './filter-dialog.component.html',
  styleUrl: './filter-dialog.component.css',
})
export class FilterDialogComponent {
  constructor(public dialogRef: MatDialogRef<FilterDialogComponent>) {
    dialogRef.disableClose = true;
  }
}
