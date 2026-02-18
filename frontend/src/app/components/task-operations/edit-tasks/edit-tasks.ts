import { Task } from './../../task';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { TaskService } from '../../task-service';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';


@Component({
  selector: 'app-edit-tasks',
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './edit-tasks.html',
  styleUrl: './edit-tasks.css',
})
export class EditTasks implements OnInit {

  formulario!: FormGroup;
  idTarefa!: string;

  constructor(private router: Router,
              private service: TaskService,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder){
                this.formulario = this.formBuilder.group({
      titulo: ['', Validators.required],
      detalhes: "",
      dia: ['', [Validators.required, Validators.min(1), Validators.max(31)]],
      mes: ['', [Validators.required, Validators.min(1), Validators.max(12)]],
      ano: ['', Validators.required],
      hora: ['', [Validators.required, Validators.min(0), Validators.max(23)]],
      minuto: ['', [Validators.required, Validators.min(0), Validators.max(59)]]
    });
  }

  ngOnInit(): void {
    const id = (this.route.snapshot.paramMap.get("id"));
    this.idTarefa = id!;
    this.buscarTarefa(this.idTarefa);
  }

  buscarTarefa(id: string){
    this.service.encontrarPorId(id).subscribe((task) => {
      this.preencherFormulario(task);
    });
  }

  preencherFormulario(task: Task) {

    const partesData = task.data.split("/");
    const partesHora = task.data.split(":");

    this.formulario.patchValue({
      titulo: task.titulo,
      detalhes: task.detalhes,
      dia: parseInt(partesData[0]),
      mes: parseInt(partesData[1]),
      ano: parseInt(partesData[2]),
      hora: parseInt(partesHora[0]),
      minuto: parseInt(partesHora[1])
    });
  }

  editarTarefa(){
    if (this.formulario.valid) {
      const f = this.formulario.value;
      const pad = (valor: number) => valor.toString().padStart(2,"0");

      const dataFormatada = `${pad(f.dia)}/${pad(f.mes)}/${pad(f.ano)}`;
      const horaFormatada = `${pad(f.hora)}:${pad(f.minuto)}`;

      const tarefaConvertida = {
        id: this.idTarefa,
        titulo: f.titulo,
        detalhes: f.detalhes,
        data: dataFormatada,
        hora: horaFormatada,
      };
      alert("tarefa editada com sucesso!");
      this.service.editar(this.idTarefa,tarefaConvertida).subscribe(() => this.router.navigate(["/listTasks"]))

    }
  }



  cancelTask(){
    const message = "Tem certeza que deseja cancelar a edição da tarefa?";

    if (window.confirm(message)){
      this.router.navigate(["/listTasks"]);
    }
  }









}
