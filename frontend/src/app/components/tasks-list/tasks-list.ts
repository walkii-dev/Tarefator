import { Component, inject } from '@angular/core';
import { TaskCard } from "../task-card/task-card";
import { Router } from "@angular/router";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-tasks-list',
  imports: [TaskCard,
            CommonModule],
  templateUrl: './tasks-list.html',
  styleUrl: './tasks-list.css',
})
export class TasksList {

  private router = inject(Router);

  navigateToCreateTask(){
    this.router.navigate(["/createTasks"]);
  }

  tasksList=[
    {
      titulo:"acordar",
      detalhes:"acordar ne pq ngm é de ferro",
      data:"09/02/2026",
      hora:"06:00"
    },
    {
      titulo:"trabalhar",
      detalhes:"trabalhar ne pq ngm é de ferro",
      data:"09/02/2026",
      hora:"08:00"
    },{
      titulo:"dormir",
      detalhes:"dormir ne pq ngm é de ferro",
      data:"09/02/2026",
      hora:"23:30"
    },
    {
      titulo:"dormir",
      detalhes:"dormir ne pq ngm é de ferro",
      data:"09/02/2026",
      hora:"23:30"
    }
  ];

}
