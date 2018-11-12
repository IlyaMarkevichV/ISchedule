import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfessorGroupInfoComponent } from './professor-group-info.component';

describe('ProfessorGroupInfoComponent', () => {
  let component: ProfessorGroupInfoComponent;
  let fixture: ComponentFixture<ProfessorGroupInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProfessorGroupInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfessorGroupInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
