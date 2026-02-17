import { Routes } from '@angular/router';
import { TasksList } from './components/task-operations/tasks-list/tasks-list';
import { CreateTasks } from './components/task-operations/create-tasks/create-tasks';
import { TaskCardDetail } from './components/task-operations/task-card-detail/task-card-detail';

export const routes: Routes = [
  {
    path:"",
    redirectTo:"listTasks",
    pathMatch:"full"
  },
  {
    path:"listTasks",
    component:TasksList
  },
  {
    path:"createTasks",
    component:CreateTasks
  },
  {
    path:"uniqueCard",
    component:TaskCardDetail
  }
];
