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

export interface Filter {
  id?: number;
  name: string;
  selection: string;
  criterias: Criteria[];
  modifiedDate: string;
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
  fieldType: string;
  operators: Operator[];
}

export interface Operator {
  operator: string;
  label: string;
}
