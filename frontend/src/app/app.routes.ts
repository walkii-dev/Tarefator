import { Routes } from '@angular/router';
import { TasksList } from './components/task-operations/tasks-list/tasks-list';
import { CreateTasks } from './components/task-operations/create-tasks/create-tasks';
import { TaskCardDetail } from './components/task-operations/task-card-detail/task-card-detail';
import { EditTasks } from './components/task-operations/edit-tasks/edit-tasks';
import { AppLogin } from './components/app-auth/app-login/app-login';
import { AppRegister } from './components/app-auth/app-register/app-register';

export const routes: Routes = [
  {
    path:"",
    redirectTo:"auth/login",
    pathMatch:"full"
  },
  {
    path:"auth/login",
    component:AppLogin
  },
  {
    path:"auth/register",
    component:AppRegister
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
    path:"uniqueCard/:id",
    component:TaskCardDetail
  },
  {
    path:"editTasks/:id",
    component:EditTasks
  }
];
