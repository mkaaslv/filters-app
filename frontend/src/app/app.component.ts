import { CommonModule, DatePipe } from '@angular/common';
import { Component } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { RouterOutlet } from '@angular/router';
import { AddFilterButtonComponent } from './components/add-filter-button/add-filter-button.component';
import { FiltersListComponent } from './filters-list/filters-list.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    MatTableModule,
    CommonModule,
    FiltersListComponent,
    AddFilterButtonComponent,
    MatToolbarModule
  ],
  providers:[DatePipe],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'frontend';
}
