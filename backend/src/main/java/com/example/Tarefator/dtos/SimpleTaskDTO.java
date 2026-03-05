package com.example.Tarefator.dtos;

import com.example.Tarefator.models.Task;

import java.util.UUID;

public record SimpleTaskDTO (UUID id,
                            String title){
    public SimpleTaskDTO(Task task){
        this(task.getId(), task.getTitle());
    }
}
