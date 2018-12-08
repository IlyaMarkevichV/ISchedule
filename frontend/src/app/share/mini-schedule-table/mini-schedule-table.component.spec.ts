import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MiniScheduleTableComponent } from './mini-schedule-table.component';

describe('MiniScheduleTableComponent', () => {
  let component: MiniScheduleTableComponent;
  let fixture: ComponentFixture<MiniScheduleTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MiniScheduleTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MiniScheduleTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
