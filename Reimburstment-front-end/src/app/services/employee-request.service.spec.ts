import { TestBed, inject } from '@angular/core/testing';

import { EmployeeRequestService } from './employee-request.service';

describe('EmployeeRequestService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [EmployeeRequestService]
    });
  });

  it('should be created', inject([EmployeeRequestService], (service: EmployeeRequestService) => {
    expect(service).toBeTruthy();
  }));
});
