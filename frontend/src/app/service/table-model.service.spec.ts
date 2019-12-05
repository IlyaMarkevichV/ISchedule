import { TestBed } from '@angular/core/testing';

import { TableModelService } from './table-model.service';

describe('TableModelService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TableModelService = TestBed.get(TableModelService);
    expect(service).toBeTruthy();
  });
});
