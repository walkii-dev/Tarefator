import { Task } from './../../task';
import { Component } from '@angular/core';
import { Router } from "@angular/router";
import { TaskService } from '../../task-service';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-create-tasks',
  imports: [FormsModule],
  templateUrl: './create-tasks.html',
  styleUrl: './create-tasks.css',
})
export class CreateTasks {
  constructor(private router: Router,private service:TaskService){}

  task: Task = {
    titulo:"",
    detalhes:"",
    data:"",
    hora:"",

  }

  earlyTask={
    title:"",
    details:"",
    dateDay:"",
    dateMonth:"",
    dateYear:"",
    timeHour:"",
    timeMinute:""
  }


  convertTask(earlyTask: any){
    const dayF = this.earlyTask.dateDay.padStart(2,"0");
    const monthF = this.earlyTask.dateMonth.padStart(2,"0");
    const yearF = this.earlyTask.dateYear;
    const hourF = this.earlyTask.timeHour.padStart(2,"0");
    const minuteF = this.earlyTask.timeMinute.padStart(2,"0");

    const convertedTask: Task ={
      titulo: this.earlyTask.title,
      detalhes: this.earlyTask.details,
      data: `${dayF}/${monthF}/${yearF}`,
      hora: `${hourF}:${minuteF}`
    };

    return convertedTask;
  }

  cancelTask(){
    const message = "Tem certeza que deseja cancelar a nova tarefa?";

    if (window.confirm(message)){
      this.router.navigate(["/listTasks"]);
    }
  }

  confirmTask(){
    let convertedTask = this.convertTask(this.earlyTask);

    alert("Tarefa criada com sucesso!");
    this.service.criar(convertedTask).subscribe(()=>
    this.router.navigate(["/listTasks"]));

  }

}
