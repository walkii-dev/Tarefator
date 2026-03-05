package com.example.Tarefator.dtos;

import com.example.Tarefator.models.Task;
import com.example.Tarefator.models.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDataDTO(UUID id,
                          String title,
                          String description,
                          LocalDateTime startTime,
                          LocalDateTime endTime,
                          TaskStatus status,
                          String owner) {
    public TaskDataDTO(Task task) {
        this (
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStartTime(),
                task.getEndTime(),
                task.getStatus(),
                task.getOwner().getEmail());
    }
}
