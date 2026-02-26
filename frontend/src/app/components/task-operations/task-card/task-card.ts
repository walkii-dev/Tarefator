import { Component, Input } from '@angular/core';
import { Task } from "../../task";
import { RouterLink } from "@angular/router";

@Component({
  selector: 'app-task-card',
  imports: [RouterLink],
  templateUrl: './task-card.html',
  styleUrl: './task-card.css',
})
export class TaskCard {

  @Input() task: Task={
    id:"",
    title:"",
    description:"",
    startTime:"",
    endTime:""
    };


}
