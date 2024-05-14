import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CriteriaType, PaginationParams } from '../../types';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root',
})
export class CriteriaTypesService {
  constructor(private apiService: ApiService) {}

  getCriteriaTypes = (
    url: string,
    params?: PaginationParams
  ): Observable<CriteriaType[]> => {
    return this.apiService.get(url, {
      params,
      responseType: 'json',
    });
  };
}
