import { Component, Input } from '@angular/core';
import { Task } from "./../task";

@Component({
  selector: 'app-task-card',
  imports: [],
  templateUrl: './task-card.html',
  styleUrl: './task-card.css',
})
export class TaskCard {

  @Input() task: Task={
    id:1,
    titulo:"oi",
    detalhes:"eae",
    data:"01/01/2001",
    hora:"18:30"
    };

}
