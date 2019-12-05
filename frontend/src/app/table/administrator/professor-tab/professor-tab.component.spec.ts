import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import {ProfessorTabComponent} from "./professor-tab.component";

describe('ProfessorComponent', () => {
  let component: ProfessorTabComponent;
  let fixture: ComponentFixture<ProfessorTabComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProfessorTabComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfessorTabComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
