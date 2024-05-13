import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Observable } from 'rxjs';
import { PaginationParams, Filters } from '../../types';

@Injectable({
  providedIn: 'root',
})
export class FiltersService {
  constructor(private apiService: ApiService) {}

  getFilters = (url: string, params?: PaginationParams): Observable<Filters> => {
    return this.apiService.get(url, {
      params,
      responseType: 'json',
    });
  };

  addFilter = (url: string, body: any): Observable<any> => {
    return this.apiService.post(url, body, {});
  };

  editFilter = (url: string, body: any): Observable<any> => {
    return this.apiService.put(url, body, {});
  };

  deleteFilter = (url: string): Observable<any> => {
    return this.apiService.delete(url, {});
  };
}
