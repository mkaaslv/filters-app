import { CommonModule, NgFor, NgIf } from '@angular/common';
import { Component, EventEmitter, Input, OnChanges, Output, SimpleChanges } from '@angular/core';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';
import { MatOptionModule } from '@angular/material/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatGridListModule } from '@angular/material/grid-list';
import { CriteriaType, Operator } from '../../../types';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-criteria-row',
  standalone: true,
  imports: [
    NgFor,
    NgIf,
    CommonModule,
    MatIconModule,
    MatInputModule,
    MatButtonModule,
    MatOptionModule,
    MatSelectModule,
    MatFormFieldModule,
    MatGridListModule,
    ReactiveFormsModule,
  ],
  templateUrl: './criteria-row.component.html',
  styleUrl: './criteria-row.component.css',
})
export class CriteriaRowComponent {
  @Input() criteriaForm!: FormGroup;
  @Input() criteriaTypes: CriteriaType[] = [];
  @Input() criteriaCount!: number;
  @Input() formInvalid!: boolean;
  @Output() remove = new EventEmitter<void>();

  valueFieldType: string = 'text';
  operators: Operator[] = [];

  constructor() {}

  ngOnInit(): void {
    const criteriaTypeControl = this.criteriaForm.get('criteriaType');
    if (criteriaTypeControl && criteriaTypeControl.value) {
      this.setRowFieldProperties(criteriaTypeControl.value);
    }
    // Listen for changes on the criteriaType form control
    this.criteriaForm.get('criteriaType')?.valueChanges.subscribe((value) => {
      this.setRowFieldProperties(+value);
    });
  }

  private setRowFieldProperties(criteriaTypeId: number): void {
    const selectedCriteriaType = this.criteriaTypes.find(
      (type) => type.id === criteriaTypeId
    );
    console.log('herere', selectedCriteriaType);
    if (selectedCriteriaType) {
      this.operators = selectedCriteriaType.operators;
      this.valueFieldType = selectedCriteriaType.fieldType || 'text';
    }
  }

  onRemove(): void {
    this.remove.emit();
  }
}
