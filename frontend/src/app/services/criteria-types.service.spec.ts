import { TestBed } from '@angular/core/testing';

import { CriteriaTypesService } from './criteria-types.service';

describe('CriteriaTypesService', () => {
  let service: CriteriaTypesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CriteriaTypesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
