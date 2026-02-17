import { Component, Input } from '@angular/core';
import { RouterLink } from "@angular/router";
import { Task } from "../../task";

@Component({
  selector: 'app-task-card-detail',
  imports: [RouterLink],
  templateUrl: './task-card-detail.html',
  styleUrl: './task-card-detail.css',
})
export class TaskCardDetail {
  @Input() task: Task={
    titulo:"",
    detalhes:"",
    data:"",
    hora:""
    };

}
