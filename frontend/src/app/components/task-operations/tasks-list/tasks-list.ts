import { Component, OnInit,  inject } from '@angular/core';
import { TaskCard } from "../task-card/task-card";
import { Router } from "@angular/router";
import { AsyncPipe, CommonModule } from '@angular/common';
import { Task } from "./../../task";
import { TaskService } from '../../../service/task-service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-tasks-list',
  imports: [TaskCard,
            CommonModule,
            AsyncPipe],
  templateUrl: './tasks-list.html',
  styleUrl: './tasks-list.css',
})
export class TasksList {

  constructor(private service:TaskService){
    this.tasksList$ = this.service.listar();
   }

  tasksList$: Observable<Task[]>;

  private router = inject(Router);

  navigateToCreateTask(){
    this.router.navigate(["/createTasks"]);
  }



}
