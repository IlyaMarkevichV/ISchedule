import { TestBed } from '@angular/core/testing';

import { AuthorizationAndTransmitService } from './authorization-and-transmit.service';

describe('AuthorizationAndTransmitService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AuthorizationAndTransmitService = TestBed.get(AuthorizationAndTransmitService);
    expect(service).toBeTruthy();
  });
});
