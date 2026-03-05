import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from "@angular/router";
import { Task } from "../../task";
import { TaskService } from '../../../service/task-service';
import { Observable } from 'rxjs';
import { AsyncPipe } from '@angular/common';


@Component({
  selector: 'app-task-card-detail',
  imports: [RouterLink,
            AsyncPipe,
  ],
  templateUrl: './task-card-detail.html',
  styleUrl: './task-card-detail.css',
})
export class TaskCardDetail implements OnInit {

  constructor(private router: Router,
              private service: TaskService,
              private route: ActivatedRoute
  ){}

    task$!: Observable<Task>;

    editTask(){
      const id = (this.route.snapshot.paramMap.get("id"));
      this.router.navigate(["/editTasks/",id]);
    }

    cancelTask(){
      const id = (this.route.snapshot.paramMap.get("id"));

      if (id && window.confirm("Deseja cancelar esta tarefa?")) {
        this.service.excluir(id).subscribe(()=>
        this.router.navigate(["/listTasks"]));
      }


    };

    ngOnInit(): void {
      const id = (this.route.snapshot.paramMap.get("id"));

      this.task$ = this.service.encontrarPorId(id!);

    }

};
