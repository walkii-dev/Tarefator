package com.example.Tarefator.controllers;

import com.example.Tarefator.dtos.TaskDTO;
import com.example.Tarefator.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    final TaskService service;

    public TaskController (TaskService service){
        this.service = service;
    }

    @PostMapping
    @Transactional
    public ResponseEntity saveTask (@RequestBody @Valid TaskDTO newTaskData, UriComponentsBuilder uriBuilder){
         var taskCreated = service.createTask(newTaskData);

        var uri = uriBuilder.path("/tasks/{id}").buildAndExpand(taskCreated.getId()).toUri();
        return ResponseEntity.created(uri).body(new TaskDTO(taskCreated));
    }

    @GetMapping("/{id}")
    public ResponseEntity getSomeTask (@PathVariable UUID id){
            var task = service.getSimpleTask(id);
        return ResponseEntity.ok(new TaskDTO(task));
    }

    @GetMapping
    public ResponseEntity listAllTasks(){
        var allTasks = service.getAllTasks();
        return ResponseEntity.ok(allTasks);
    }

    @PutMapping
    @Transactional
    public ResponseEntity editTask (@RequestBody @Valid TaskDTO editedTask){
        var updatedTask = service.editTask(editedTask);
    return ResponseEntity.ok(new TaskDTO(updatedTask));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable UUID id){
         service.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

}
