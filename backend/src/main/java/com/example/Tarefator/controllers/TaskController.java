package com.example.Tarefator.controllers;

import com.example.Tarefator.dtos.TaskDTO;
import com.example.Tarefator.services.TaskService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
    public ResponseEntity saveTask (@RequestBody @Valid TaskDTO newTaskData,
                                    @AuthenticationPrincipal UserDetails loggedUser,
                                    UriComponentsBuilder uriBuilder){
        var taskCreated = service.createTask(newTaskData,loggedUser);
        var uri = uriBuilder.path("/tasks/{id}").buildAndExpand(taskCreated.getId()).toUri();
        return ResponseEntity.created(uri).body(new TaskDTO(taskCreated));
    }

    @GetMapping("/{id}")
    public ResponseEntity getSomeTask (@PathVariable UUID id){
            var task = service.getSimpleTask(id);
        return ResponseEntity.ok(task);
    }
/*
    É Necessário adicionar paginação, a fim de pesquisar as tarefas por status (Done, Cancelled, Current)
 */
    @GetMapping
    public ResponseEntity listAllTasks(){
        var allTasks = service.getAllTasks();
        return ResponseEntity.ok(allTasks);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity editTask (@RequestBody @Valid TaskDTO editedTask, @PathVariable UUID id){
        var updatedTask = service.editTask(editedTask);
    return ResponseEntity.ok(new TaskDTO(updatedTask));
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
