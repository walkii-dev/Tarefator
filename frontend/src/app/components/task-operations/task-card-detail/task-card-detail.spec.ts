import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TaskCardDetail } from './task-card-detail';

describe('TaskCardDetail', () => {
  let component: TaskCardDetail;
  let fixture: ComponentFixture<TaskCardDetail>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TaskCardDetail]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TaskCardDetail);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
