import { Component, EventEmitter, Inject, Input, Output } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import {
  MatDialogModule,
  MatDialogRef,
  MAT_DIALOG_DATA,
} from '@angular/material/dialog';
import { MatFormField } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatRadioButton } from '@angular/material/radio';
import { FilterEditorComponent } from '../filter-editor/filter-editor.component';
import { ResizableModule, ResizeEvent } from 'angular-resizable-element';
import { NgIf, NgStyle } from '@angular/common';
import { DialogService } from '../../services/dialog.service';

@Component({
  selector: 'app-filter-dialog',
  standalone: true,
  imports: [
    NgIf,
    NgStyle,
    FilterEditorComponent,
    MatIconModule,
    MatFormField,
    MatRadioButton,
    MatDialogModule,
    MatButtonModule,
    ResizableModule,
  ],
  templateUrl: './filter-dialog.component.html',
  styleUrl: './filter-dialog.component.css',
})
export class FilterDialogComponent {
  @Input() formData: any;
  @Output() id: EventEmitter<number> = new EventEmitter<number>();

  openModal!: boolean;
  filterId?: number;

  constructor(
    private dialogService: DialogService,
    public dialogRef: MatDialogRef<FilterDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data?: any
  ) {
    if (data) {
      this.filterId = data.filterId;
      this.openModal = data.openModal;
    }
    dialogRef.disableClose = true;
  }

  sendDataToForm(): void {
    this.id.emit(this.filterId);
  }

  emitOpenNonModal(value: boolean) {
    this.openModal = false;
    this.dialogRef.close()
    this.dialogService.setOpenNonModal(value);
  }
}
