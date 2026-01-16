package com.example.Tarefator.dtos;

import com.example.Tarefator.models.Task;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class TaskDTO{

    private UUID id;
    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public TaskDTO(UUID id, String title, String description, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public TaskDTO(Task task){
        this(task.getId(), task.getTitle(), task.getDescription(), task.getStartTime(), task.getEndTime());
    }

    public TaskDTO() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TaskDTO taskDTO = (TaskDTO) o;
        return Objects.equals(getId(), taskDTO.getId()) && Objects.equals(getTitle(), taskDTO.getTitle()) && Objects.equals(getDescription(), taskDTO.getDescription()) && Objects.equals(getStartTime(), taskDTO.getStartTime()) && Objects.equals(getEndTime(), taskDTO.getEndTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(), getStartTime(), getEndTime());
    }
}
