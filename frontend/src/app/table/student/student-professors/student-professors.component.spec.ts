import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentProfessorsComponent } from './student-professors.component';

describe('StudentProfessorsComponent', () => {
  let component: StudentProfessorsComponent;
  let fixture: ComponentFixture<StudentProfessorsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StudentProfessorsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StudentProfessorsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
