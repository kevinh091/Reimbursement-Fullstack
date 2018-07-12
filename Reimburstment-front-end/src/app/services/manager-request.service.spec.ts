import { TestBed, inject } from '@angular/core/testing';

import { ManagerRequestService } from './manager-request.service';

describe('ManagerRequestService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ManagerRequestService]
    });
  });

  it('should be created', inject([ManagerRequestService], (service: ManagerRequestService) => {
    expect(service).toBeTruthy();
  }));
});
