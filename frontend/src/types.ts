import { HttpContext, HttpHeaders, HttpParams } from '@angular/common/http';

export interface Options {
  headers?:
    | HttpHeaders
    | {
        [header: string]: string | string[];
      };
  observe?: 'body';
  context?: HttpContext;
  params?:
    | HttpParams
    | {
        [param: string]:
          | string
          | number
          | boolean
          | ReadonlyArray<string | number | boolean>;
      };
  reportProgress?: boolean;
  responseType?: 'json';
  withCredentials?: boolean;
  transferCache?:
    | {
        includeHeaders?: string[];
      }
    | boolean;
}

export interface Filters {
  items: Filter[];
  total: number;
  pageNumber: number;
  perPage: number;
  totalPages: number;
}

export interface Filter {
  id?: number;
  name: string;
  selection: number;
  criterias: Criteria[];
  createdDate: string;
  modifiedDate: string | null;
}

export interface Criteria {
  id?: number;
  filterId: number;
  criteriaType: number;
  operator: string;
  value: string | number;
}

export interface CriteriaType {
  id: number;
  label: string;
  operators: string[];
}

export interface PaginationParams {
  [param: string]:
    | string
    | number
    | boolean
    | ReadonlyArray<string | number | boolean>;
  pageNumber: number;
  perPage: number;
}