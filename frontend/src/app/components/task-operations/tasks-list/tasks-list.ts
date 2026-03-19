import { Component, OnInit,  inject } from '@angular/core';
import { TaskCard } from "../task-card/task-card";
import { Router } from "@angular/router";
import { AsyncPipe, CommonModule } from '@angular/common';
import { Task } from "./../../task";
import { TaskService } from '../../../service/task-service';
import { BehaviorSubject, Observable, switchMap } from 'rxjs';

@Component({
  selector: 'app-tasks-list',
  imports: [TaskCard,
            CommonModule,
            AsyncPipe],
  templateUrl: './tasks-list.html',
  styleUrl: './tasks-list.css',
})
export class TasksList {

  currentFilterLabel = "Todas";

  private readonly statusMap: Record<string,string> = {
    'Todas': '',
    'Concluídas': 'DONE',
    'Pendente': 'CURRENT',
    'Canceladas': 'CANCELLED'
  }

  private statusSubject = new BehaviorSubject<string>('');

  tasksList$: Observable<Task[]> = this.statusSubject.pipe(
    switchMap(statusEnum => {
      return this.service.listarPorFiltro(statusEnum);
    })
  );

  setFilter(label: string) {
    this.currentFilterLabel = label;
    const enumValue = this.statusMap[label]; // Traduz o clique para o formato do Back-end
    this.statusSubject.next(enumValue);
  }


  constructor(private service:TaskService){
    this.tasksList$ = this.service.listar();
   }



  private router = inject(Router);

  navigateToCreateTask(){
    this.router.navigate(["/createTasks"]);
  }



}
