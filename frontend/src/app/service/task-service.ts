import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { Task } from '../components/task';
import { Observable, map } from 'rxjs';

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

  listarPorFiltro(statusEnum?: string, page: number = 0, size: number = 9): Observable<Task[]> {
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
    if (statusEnum) {
      params = params.set('status', statusEnum);
    }
    return this.http.get<any>(`${this.API}`, { params }).pipe(
      map(response => response.content)
    );
  }

}
