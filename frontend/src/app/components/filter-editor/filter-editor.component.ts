import { CommonModule, NgFor } from '@angular/common';
import {
  Component, Input,
  ViewChild
} from '@angular/core';
import {
  FormArray,
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogRef } from '@angular/material/dialog';
import {
  MatFormField,
  MatFormFieldModule,
  MatLabel
} from '@angular/material/form-field';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatRadioModule } from '@angular/material/radio';
import { CriteriaType, Filter } from '../../../types';
import { CriteriaTypesService } from '../../services/criteria-types.service';
import { FiltersService } from '../../services/filters.service';
import { CriteriaRowComponent } from '../criteria-row/criteria-row.component';
import { FilterDialogComponent } from '../filter-dialog/filter-dialog.component';

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
    MatGridListModule,
    MatIconModule,
  ],
  templateUrl: './filter-editor.component.html',
  styleUrl: './filter-editor.component.css',
})
export class FilterEditorComponent {
  filterForm!: FormGroup;
  criteriaTypes: CriteriaType[] = [];
  baseUrl: string = 'http://localhost:8080/api/v1';
  filter?: Filter;

  @Input() id?: number;

  constructor(
    private fb: FormBuilder,
    private filterService: FiltersService,
    private criteriaTypesService: CriteriaTypesService,
    private dialogRef: MatDialogRef<FilterDialogComponent>
  ) {}

  ngOnInit(): void {
    this.fetchCriteriaTypes();

    if (!this.id) {
      this.filterForm = this.fb.group({
        id: [''],
        name: ['', Validators.required],
        selection: ['1', Validators.required],
        criterias: this.fb.array([this.createCriteria()]),
      });

      return;
    }

    if (!this.filter) this.fetchFilter();
    console.log('this.filter', this.filter);
    this.filterForm = this.fb.group({
      id: [this.filter!.id],
      name: [this.filter!.name, Validators.required],
      selection: [this.filter!.selection, Validators.required],
      criterias: this.fb.array([]),
    });

    const criteriasArray = this.filterForm.get('criterias') as FormArray;
    this.filter!.criterias.forEach((criteria) => {
      criteriasArray.push(
        this.fb.group({
          criteriaType: [criteria.criteriaType, Validators.required],
          operator: [criteria.operator, Validators.required],
          value: [criteria.value, Validators.required],
        })
      );
    });
    console.log('form', this.filterForm);
  }

  getCriteriaControls(formArrayName: string): any[] {
    const formArray = this.filterForm.get(formArrayName) as FormArray;
    return formArray.controls;
  }

  fetchFilter(): void {
    this.filterService
      .getFilter(`${this.baseUrl}/filters/${this.id}`)
      .subscribe({
        next: (data) => {
          console.log(data);
        },
        error: (error) => {
          console.log(error);
        },
      });
  }

  fetchCriteriaTypes(): void {
    this.criteriaTypesService
      .getCriteriaTypes(`${this.baseUrl}/criteria-types`)
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
      if (this.filterForm.value.id === '') {
        // If filterId is empty, remove it from the form group
        this.filterForm.removeControl('id');
      }
      console.log(this.filterForm.value);

      this.filterService.addFilter(`${this.baseUrl}/filters`, this.filterForm.value).subscribe({
        next: (data) => {
          console.log(data);
          this.dialogRef.close();
        },
        error: (error) => {
          console.log(error);
        },
      });
    }
  }
}
