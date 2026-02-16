import { Component, OnInit,  inject } from '@angular/core';
import { TaskCard } from "../task-card/task-card";
import { Router } from "@angular/router";
import { CommonModule } from '@angular/common';
import { Task } from "./../task";
import { TaskService } from '../task-service';

@Component({
  selector: 'app-tasks-list',
  imports: [TaskCard,
            CommonModule],
  templateUrl: './tasks-list.html',
  styleUrl: './tasks-list.css',
})
export class TasksList implements OnInit {

  private router = inject(Router);

  navigateToCreateTask(){
    this.router.navigate(["/createTasks"]);
  }

  tasksList: Task[] = [];

  constructor(private service:TaskService){ }

  ngOnInit(): void {
    this.service.listar().subscribe((tasksList) =>{
      this.tasksList = tasksList;
    })
  }

}
