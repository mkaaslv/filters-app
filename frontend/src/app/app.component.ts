import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { FiltersListComponent } from './filters-list/filters-list.component';
import { MatTableModule } from '@angular/material/table';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, FiltersListComponent, MatTableModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'frontend';
}
