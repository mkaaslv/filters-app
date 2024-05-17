import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Observable, Subject } from 'rxjs';
import { Filter } from '../../types';

@Injectable({
  providedIn: 'root',
})
export class FiltersService {
  private reloadListSubject: Subject<void> = new Subject<void>();
  reloadList$: Observable<void> = this.reloadListSubject.asObservable();

  constructor(private apiService: ApiService) {}

  getFilters = (url: string): Observable<Filter[]> => {
    return this.apiService.get(url, {});
  };

  getFilter = (url: string): Observable<Filter> => {
    return this.apiService.get(url, {});
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

  reloadList(): void {
    this.reloadListSubject.next();
  }
}
