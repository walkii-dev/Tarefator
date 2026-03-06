package com.example.Tarefator.controllers;

import com.example.Tarefator.dtos.TaskDataDTO;
import com.example.Tarefator.models.TaskStatus;
import com.example.Tarefator.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {

    final TaskService service;

    public TaskController (TaskService service){
        this.service = service;
    }

    @PostMapping
    @Transactional
    public ResponseEntity saveTask (@RequestBody @Valid TaskDataDTO newTaskData,
                                    Authentication authentication,
                                    UriComponentsBuilder uriBuilder){
        var taskCreated = service.createTask(newTaskData,authentication);
        var uri = uriBuilder.path("/tasks/{id}").buildAndExpand(taskCreated.getId()).toUri();
        return ResponseEntity.created(uri).body(new TaskDataDTO(taskCreated));
    }

    @GetMapping("/{id}")
    public ResponseEntity getSomeTask (@PathVariable UUID id){
            var task = service.getSimpleTask(id);
        return ResponseEntity.ok(task);
    }
/*
    Paginação feita, exemplos de como pesquisar por tarefa, só fazer get para:
    http://localhost:8080/tasks?status=DONE
    http://localhost:8080/tasks?status=CREATED
    http://localhost:8080/tasks?status=CREATED&status=EDITED(a fazer)
    http://localhost:8080/tasks?status=CURRENT
    http://localhost:8080/tasks?status=EXPIRED
    http://localhost:8080/tasks?status=CANCELLED
 */
    @GetMapping
    public ResponseEntity listAllTasks(
            @RequestParam(value="status",required = false) TaskStatus status,
            @RequestParam(value = "page", required = false, defaultValue = "0")int page,
            @RequestParam(value = "size",required = false,defaultValue = "9")int size){
        var allTasks = service.getAllTasks(status,page,size);
        return ResponseEntity.ok(allTasks);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity editTask (@RequestBody @Valid TaskDataDTO editedTask, @PathVariable UUID id){
        var updatedTask = service.editTask(editedTask);
    return ResponseEntity.ok(new TaskDataDTO(updatedTask));
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity markAsDone (@PathVariable UUID id){
        var task = service.markTaskAsDone(id);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable UUID id){
         service.cancelTask(id);
        return ResponseEntity.noContent().build();
    }
}
