import { Routes } from '@angular/router';
import { TasksList } from './components/task-operations/tasks-list/tasks-list';
import { CreateTasks } from './components/task-operations/create-tasks/create-tasks';

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
  }
];
