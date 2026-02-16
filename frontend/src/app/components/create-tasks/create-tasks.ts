import { Component } from '@angular/core';
import { Router } from "@angular/router";

@Component({
  selector: 'app-create-tasks',
  imports: [],
  templateUrl: './create-tasks.html',
  styleUrl: './create-tasks.css',
})
export class CreateTasks {
  constructor(private router: Router){}


  cancelTask(){
    const message = "Tem certeza que deseja cancelar a nova tarefa?";

    if (window.confirm(message)){
      this.router.navigate(["/listTasks"]);
    }
  }

  confirmTask(){
    alert("Tarefa criada com sucesso!");
    this.router.navigate(["/listTasks"]);
  }

}
