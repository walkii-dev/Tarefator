import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-task-card',
  imports: [],
  templateUrl: './task-card.html',
  styleUrl: './task-card.css',
})
export class TaskCard {

  @Input() task={
    titulo:"oi",
    detalhes:"eae",
    data:"01/01/2001",
    hora:"18:30"
    };

}
