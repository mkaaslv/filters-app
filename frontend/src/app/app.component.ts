import { CommonModule, DatePipe, NgClass } from '@angular/common';
import {
  Component,
  ElementRef,
  OnDestroy,
  OnInit,
  Renderer2,
  ViewChild,
} from '@angular/core';
import {
  MatDialog,
  MatDialogModule,
  MatDialogRef,
} from '@angular/material/dialog';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { RouterOutlet } from '@angular/router';
import { AddFilterButtonComponent } from './components/add-filter-button/add-filter-button.component';
import { FilterDialogComponent } from './components/filter-dialog/filter-dialog.component';
import { FiltersListComponent } from './components/filters-list/filters-list.component';
import { DialogService } from './services/dialog.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    NgClass,
    RouterOutlet,
    MatTableModule,
    CommonModule,
    FiltersListComponent,
    AddFilterButtonComponent,
    FilterDialogComponent,
    MatToolbarModule,
    MatDialogModule,
    FilterDialogComponent,
  ],
  providers: [DatePipe],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent implements OnInit, OnDestroy {
  title = 'frontend';
  openNonModal: boolean = false;
  dialogRef: any;

  @ViewChild('dialogPlaceholder', { static: false })
  dialogPlaceholder!: ElementRef;

  constructor(
    private dialogService: DialogService,
    private dialog: MatDialog,
    private renderer: Renderer2,
    private el: ElementRef
  ) {}

  ngOnInit() {
    this.dialogService.openNonModal$.subscribe((value) => {
      this.openNonModal = value;
      if (value) {
        this.openDialog();
      } else {
        this.closeDialog();
      }
    });
  }

  openDialog() {
    this.dialogRef = this.dialog.open(FilterDialogComponent, {
      panelClass: 'non-modal-dialog',
      hasBackdrop: false,
      data: { openModal: false },
    });
    const dialogElement =
      this.dialogRef._containerInstance._elementRef.nativeElement;
    this.renderer.appendChild(
      this.dialogPlaceholder.nativeElement,
      dialogElement
    );
  }

  closeDialog() {
    if (this.dialogRef) {
      this.openNonModal = false;
      this.dialogRef.close();
    }
  }

  ngOnDestroy() {
    this.closeDialog();
  }
}
