import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StaffphotosComponent } from './staffphotos.component';

describe('StaffphotosComponent', () => {
  let component: StaffphotosComponent;
  let fixture: ComponentFixture<StaffphotosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StaffphotosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StaffphotosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
