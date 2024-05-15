import { CommonModule, NgFor, NgIf } from '@angular/common';
import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';
import { MatOptionModule } from '@angular/material/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatGridListModule } from '@angular/material/grid-list';
import { CriteriaType, Operator } from '../../../types';

@Component({
  selector: 'app-criteria-row',
  standalone: true,
  imports: [
    NgFor,
    NgIf,
    CommonModule,
    MatInputModule,
    MatOptionModule,
    MatSelectModule,
    MatFormFieldModule,
    MatGridListModule,
    ReactiveFormsModule,
  ],
  templateUrl: './criteria-row.component.html',
  styleUrl: './criteria-row.component.css',
})
export class CriteriaRowComponent implements OnChanges {
  @Input() criteriaForm!: FormGroup;
  @Input() criteriaTypes: CriteriaType[] = [];

  valueFieldType: string = 'text';
  operators: Operator[] = [];

  constructor() {}

  ngOnChanges(changes: SimpleChanges) {
    if (changes['criteriaForm'] && changes['criteriaForm'].currentValue) {
      this.setRowFieldProperties(1);
    }
  }

  ngOnInit(): void {
    const criteriaTypeControl = this.criteriaForm.get('criteriaType');
    if (criteriaTypeControl && criteriaTypeControl.value) {
      this.setRowFieldProperties(criteriaTypeControl.value);
    }
    // Listen for changes on the criteriaType form control
    this.criteriaForm.get('criteriaType')?.valueChanges.subscribe((value) => {
      this.setRowFieldProperties(value);
    });
  }

  private setRowFieldProperties(criteriaTypeId: number): void {
    const selectedCriteriaType = this.criteriaTypes.find(
      (type) => type.id === criteriaTypeId
    );
    if (selectedCriteriaType) {
      this.operators = selectedCriteriaType.operators;
      this.valueFieldType = selectedCriteriaType.fieldType || 'text';
    }
  }
}
