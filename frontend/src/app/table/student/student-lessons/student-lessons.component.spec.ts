import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentLessonsComponent } from './student-lessons.component';

describe('StudentLessonsComponent', () => {
  let component: StudentLessonsComponent;
  let fixture: ComponentFixture<StudentLessonsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StudentLessonsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StudentLessonsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
