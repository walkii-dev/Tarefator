import { HttpClient } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { Task } from './task';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TaskService {

  private readonly API = "http://localhost:8080/tasks"

  constructor(private http: HttpClient){ }

  listar(): Observable<Task[]> {
    return this.http.get<Task[]>(this.API);
  }

  criar(task: Task): Observable<any> {
    return this.http.post(this.API, task);
  }

  editar(id:string ,task: Task): Observable<Task> {
    const url = `${this.API}/${id}`;
    console.log(task);
    return this.http.put<Task>(url,task);
  }

  excluir(id: string): Observable<Task>{
    const url = `${this.API}/${id}`;
    return this.http.delete<Task>(url);
  }

  encontrarPorId(id: string): Observable<Task>{
    const url = `${this.API}/${id}`;
    return this.http.get<Task>(url);
  }

}
