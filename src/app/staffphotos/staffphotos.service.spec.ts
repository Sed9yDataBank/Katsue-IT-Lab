import { TestBed } from '@angular/core/testing';

import { StaffphotosService } from './staffphotos.service';

describe('StaffphotosService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: StaffphotosService = TestBed.get(StaffphotosService);
    expect(service).toBeTruthy();
  });
});
