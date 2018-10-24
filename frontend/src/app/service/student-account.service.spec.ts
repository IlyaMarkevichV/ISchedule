import { TestBed } from '@angular/core/testing';

import { StudentAccountService } from './student-account.service';

describe('StudentAccountService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: StudentAccountService = TestBed.get(StudentAccountService);
    expect(service).toBeTruthy();
  });
});
