import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CriteriaType } from '../../types';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root',
})
export class CriteriaTypesService {
  constructor(private apiService: ApiService) {}

  getCriteriaTypes = (url: string): Observable<CriteriaType[]> => {
    return this.apiService.get(url, {});
  };
}
