package com.example.Tarefator.dtos;

import com.example.Tarefator.models.AppUser;
import com.example.Tarefator.models.Task;
import com.example.Tarefator.models.TaskStatus;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class TaskDTO {

    private UUID id;
    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private TaskStatus status;
    private AppUser owner;

    public TaskDTO(UUID id,String title, String description, LocalDateTime startTime, LocalDateTime endTime,TaskStatus status,AppUser owner) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.owner = owner;
    }
    public TaskDTO (Task task){
        this(task.getId(), task.getTitle(), task.getDescription(),task.getStartTime(),task.getEndTime(),task.getStatus(),task.getOwner());
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

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public AppUser getOwner() {
        return owner;
    }

    public void setOwner(AppUser owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TaskDTO taskDTO = (TaskDTO) o;
        return Objects.equals(id, taskDTO.id) && Objects.equals(title, taskDTO.title) && Objects.equals(description, taskDTO.description) && Objects.equals(startTime, taskDTO.startTime) && Objects.equals(endTime, taskDTO.endTime) && status == taskDTO.status && Objects.equals(owner, taskDTO.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, startTime, endTime, status, owner);
    }
}
