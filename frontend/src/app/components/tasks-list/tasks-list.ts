import { Component } from '@angular/core';
import { TaskCard } from "../task-card/task-card";

@Component({
  selector: 'app-tasks-list',
  imports: [TaskCard],
  templateUrl: './tasks-list.html',
  styleUrl: './tasks-list.css',
})
export class TasksList {

}
