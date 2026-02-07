import { Routes } from '@angular/router';
import { TasksList } from './components/tasks-list/tasks-list';
import { CreateTasks } from './components/create-tasks/create-tasks';

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
