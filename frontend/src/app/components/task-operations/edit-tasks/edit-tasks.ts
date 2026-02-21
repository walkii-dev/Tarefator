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
      title: ['', Validators.required],
      description: "",

      startDay: ['', [Validators.required, Validators.min(1), Validators.max(31)]],
      startMonth: ['', [Validators.required, Validators.min(1), Validators.max(12)]],
      startYear: ['', Validators.required],
      startHour: ['', [Validators.required, Validators.min(0), Validators.max(23)]],
      startMinute: ['', [Validators.required, Validators.min(0), Validators.max(59)]],

      endDay: ['', [Validators.required, Validators.min(1), Validators.max(31)]],
      endMonth: ['', [Validators.required, Validators.min(1), Validators.max(12)]],
      endYear: ['', Validators.required],
      endHour: ['', [Validators.required, Validators.min(0), Validators.max(23)]],
      endMinute: ['', [Validators.required, Validators.min(0), Validators.max(59)]]
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

    const parseStartDateTime = task.startTime.split("T");

    const parseStartDate = parseStartDateTime[0].split("-");
    const parseStartTime = parseStartDateTime[1].split(":");

    const parseEndDateTime = task.endTime.split("T");

    const parseEndDate = parseEndDateTime[0].split("-");
    const parseEndTime = parseEndDateTime[1].split(":");


    this.formulario.patchValue({
      title: task.title,
      description: task.description,
      startDay: parseInt(parseStartDate[2]),
      startMonth: parseInt(parseStartDate[1]),
      startYear: parseInt(parseStartDate[0]),
      startHour: parseInt(parseStartTime[0]),
      startMinute: parseInt(parseStartTime[1]),

      endDay: parseInt(parseEndDate[2]),
      endMonth: parseInt(parseEndDate[1]),
      endYear: parseInt(parseEndDate[0]),
      endHour: parseInt(parseEndTime[0]),
      endMinute: parseInt(parseEndTime[1])
    });
  }

  editarTarefa(){
    if (this.formulario.valid) {
      const f = this.formulario.value;
      const pad = (valor: number) => valor.toString().padStart(2,"0");

      const formattedStartDatetime = `${pad(f.startYear)}-${pad(f.startMonth)}-${pad(f.startDay)}T${pad(f.startHour)}:${pad(f.startMinute)}:00`;
      const formattedEndDatetime = `${pad(f.endYear)}-${pad(f.endMonth)}-${pad(f.endDay)}T${pad(f.endHour)}:${pad(f.endMinute)}:00`;



      const tarefaConvertida = {
        id: this.idTarefa,
        title: f.title,
        description:f.description,
        startTime: formattedStartDatetime,
        endTime: formattedEndDatetime,
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
