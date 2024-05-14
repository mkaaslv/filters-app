import { CommonModule, NgFor } from '@angular/common';
import { Component } from '@angular/core';
import {
  FormArray,
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import {
  MatFormField,
  MatFormFieldModule,
  MatLabel
} from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatRadioModule } from '@angular/material/radio';
import { CriteriaType } from '../../../types';
import { CriteriaTypesService } from '../../services/criteria-types.service';
import { CriteriaRowComponent } from '../criteria-row/criteria-row.component';

@Component({
  selector: 'app-filter-editor',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    CriteriaRowComponent,
    MatRadioModule,
    NgFor,
    CommonModule,
    MatButtonModule,
    MatFormField,
    MatLabel,
    MatInputModule,
    MatFormFieldModule,
  ],
  templateUrl: './filter-editor.component.html',
  styleUrl: './filter-editor.component.css',
})
export class FilterEditorComponent {
  filterForm!: FormGroup;
  criteriaTypes: CriteriaType[] = [];

  constructor(
    private fb: FormBuilder,
    private criteriaTypesService: CriteriaTypesService
  ) {}

  ngOnInit(): void {
    this.filterForm = this.fb.group({
      name: ['', Validators.required],
      selection: ['1', Validators.required],
      criterias: this.fb.array([this.createCriteria()]),
    });

    this.fetchCriteriaTypes();
  }

  getCriteriaControls(formArrayName: string): any[] {
    const formArray = this.filterForm.get(formArrayName) as FormArray;
    return formArray.controls;
  }

  fetchCriteriaTypes(): void {
    this.criteriaTypesService
      .getCriteriaTypes('http://localhost:8080/api/v1/criteria-types')
      .subscribe((types) => {
        this.criteriaTypes = types;
      });
  }

  createCriteria(): FormGroup {
    return this.fb.group({
      criteriaType: [1, Validators.required],
      operator: ['MORE_THAN', Validators.required],
      value: ['', Validators.required],
    });
  }

  addCriteria(): void {
    const criterias = this.filterForm.get('criterias') as FormArray;
    criterias.push(this.createCriteria());
  }

  removeCriteria(index: number): void {
    const criterias = this.filterForm.get('criterias') as FormArray;
    criterias.removeAt(index);
  }

  onSubmit(): void {
    if (this.filterForm.valid) {
      console.log(this.filterForm.value);
      // Send the form data to your backend or perform any other actions here
    }
  }
}
