import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfessorGroupScheduleComponent } from './professor-group-schedule.component';

describe('ProfessorGroupScheduleComponent', () => {
  let component: ProfessorGroupScheduleComponent;
  let fixture: ComponentFixture<ProfessorGroupScheduleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProfessorGroupScheduleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfessorGroupScheduleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
