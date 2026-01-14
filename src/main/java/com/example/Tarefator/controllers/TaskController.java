package com.example.Tarefator.controllers;

import com.example.Tarefator.dtos.TaskDTO;
import com.example.Tarefator.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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
}
