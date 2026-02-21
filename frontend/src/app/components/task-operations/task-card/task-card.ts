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

    markAsDone(){
      alert("tarefa marcada como concluída!");
      // se a tarefa já estiver expirada, o botão deve mudar! se for cancelada ele nem aparece (mudar status do botão)
    }
}
