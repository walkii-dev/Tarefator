package com.example.Tarefator.services;

import com.example.Tarefator.exceptions.InvalidTaskDataException;
import com.example.Tarefator.mappers.TaskMapper;
import com.example.Tarefator.models.Task;
import com.example.Tarefator.repositories.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.example.Tarefator.dtos.TaskDTO;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    final TaskRepository repository;
    final TaskMapper mapper;

    public TaskService (TaskRepository repository, TaskMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }


    Logger logger = LoggerFactory.getLogger(TaskService.class.getName());

    public Task createTask (TaskDTO taskData){
        logger.info("mapping dto to entity and saving in database.");

        try{
            validateTaskTime(taskData);
        } catch (InvalidTaskDataException ex) {
            throw new InvalidTaskDataException(ex.getMessage());
        }

        var convertedTask = mapper.toTaskEntity(taskData);
        repository.save(convertedTask);
        return convertedTask;
    }


    public Task getSimpleTask(UUID id){
        logger.info("finding a unique task saved on database.");
        var findTask = repository.getReferenceById(id);
        return findTask;
    }

    public List<Task> getAllTasks() {
        logger.info("finding for all tasks saved in database.");
        var tasksList = repository.findAll();
        return tasksList;
    }

    public Task editTask(TaskDTO editedTask) {
        var taskToEdit = repository.getReferenceById(editedTask.getId());
        repository.save(taskToEdit);
        return taskToEdit;
    }

    public void deleteTask(UUID id) {
        var findTask = repository.getReferenceById(id);
        repository.delete(findTask);
    }
    // função que valida se a data da tarefa informada está válida
    public boolean validateTaskTime(TaskDTO dto){
        return switch (dto) {
            case TaskDTO d when d.getStartTime().isAfter(d.getEndTime()) ->
                    throw new InvalidTaskDataException("a data de início está inválida.");

            case TaskDTO d when d.getStartTime().isEqual(d.getEndTime()) ->
                    throw new InvalidTaskDataException("seu burro, tarefa na mesma hora que começa termina?");

            case TaskDTO d when d.getStartTime().isBefore(LocalDateTime.ofInstant(Instant.now(),ZoneId.systemDefault())) ->
                    throw new InvalidTaskDataException("a tarefa é para o passado seu burro? não dá kkk");
            case null, default -> true;
        };
    }
}
