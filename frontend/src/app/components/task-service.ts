import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Task } from './task';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TaskService {

  private readonly API = "http://localhost:3000/tasksList"

  constructor(private http: HttpClient){ }

  listar(): Observable<Task[]> {
    return this.http.get<Task[]>(this.API);
  }

}
