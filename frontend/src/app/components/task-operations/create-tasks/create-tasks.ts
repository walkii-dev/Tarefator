import { Task } from './../../task';
import { Component } from '@angular/core';
import { Router } from "@angular/router";
import { TaskService } from '../../../service/task-service';
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
    title:"",
    description:"",
    startTime:"",
    endTime:"",

  }

  earlyTask={
    title:"",
    details:"",

    startDay:"",
    startMonth:"",
    startYear:"",

    startHour:"",
    startMinute:"",

    endDay:"",
    endMonth:"",
    endYear:"",

    endHour:"",
    endMinute:""
  }


  convertTask(earlyTask: any){
    const daySF = this.earlyTask.startDay.padStart(2,"0");
    const monthSF = this.earlyTask.startMonth.padStart(2,"0");
    const yearSF = this.earlyTask.startYear;
    const hourSF = this.earlyTask.startHour.padStart(2,"0");
    const minuteSF = this.earlyTask.startMinute.padStart(2,"0");

    const dayEF = this.earlyTask.endDay.padStart(2,"0");
    const monthEF = this.earlyTask.endMonth.padStart(2,"0");
    const yearEF = this.earlyTask.endYear;
    const hourEF = this.earlyTask.endHour.padStart(2,"0");
    const minuteEF = this.earlyTask.endMinute.padStart(2,"0");

    const convertedTask: Task ={
      title: this.earlyTask.title,
      description: this.earlyTask.details,
      startTime: `${yearSF}-${monthSF}-${daySF}T${hourSF}:${minuteSF}:00`,
      endTime: `${yearEF}-${monthEF}-${dayEF}T${hourEF}:${minuteEF}:00`,
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
