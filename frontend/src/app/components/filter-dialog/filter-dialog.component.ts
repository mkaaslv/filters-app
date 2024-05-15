import { Component, EventEmitter, Inject, Input, Output } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatFormField } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatRadioButton } from '@angular/material/radio';
import { FilterEditorComponent } from '../filter-editor/filter-editor.component';
import { CdkDrag } from '@angular/cdk/drag-drop';
import { Filter } from '../../../types';

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
    CdkDrag
  ],
  templateUrl: './filter-dialog.component.html',
  styleUrl: './filter-dialog.component.css',
})
export class FilterDialogComponent {
  @Input() formData: any;
  filterId?: number;
  @Output() id: EventEmitter<number> = new EventEmitter<number>();

  constructor(public dialogRef: MatDialogRef<FilterDialogComponent>, @Inject(MAT_DIALOG_DATA) public data?: any) {
    if (data) {
      this.filterId = data.filterId;
    }
    dialogRef.disableClose = true;
  }

  sendDataToForm(): void {
    this.id.emit(this.filterId);
  }
}
