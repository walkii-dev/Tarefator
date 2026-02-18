import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditTasks } from './edit-tasks';

describe('EditTasks', () => {
  let component: EditTasks;
  let fixture: ComponentFixture<EditTasks>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditTasks]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditTasks);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
