package com.example.Tarefator.dtos;

import com.example.Tarefator.models.Task;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDTO(UUID id,
                      @NotBlank(message = "have be a title on new tasks.")
                      String title,
                      @NotNull
                      String description,
                      LocalDateTime startTime,
                      LocalDateTime endTime) {
    public TaskDTO(Task taskCreated) {
        this(taskCreated.getId(),taskCreated.getTitle(), taskCreated.getDescription(), taskCreated.getStartTime(),taskCreated.getEndTime());
    }
}
