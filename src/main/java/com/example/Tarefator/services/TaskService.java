package com.example.Tarefator.services;

import com.example.Tarefator.exceptions.InvalidTaskDataException;
import com.example.Tarefator.mappers.TaskMapper;
import com.example.Tarefator.models.Task;
import com.example.Tarefator.repositories.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.example.Tarefator.dtos.TaskDTO;

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
        var convertedTask = mapper.toTaskEntity(taskData);

        try{
            validateTaskTime(convertedTask);
        } catch (InvalidTaskDataException ex) {
            throw new RuntimeException(ex);
        }
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
        var taskToEdit = repository.getReferenceById(editedTask.id());
        repository.save(taskToEdit);
        return taskToEdit;
    }

    public void deleteTask(UUID id) {
        var findTask = repository.getReferenceById(id);
        repository.delete(findTask);
    }
    // função que valida se a data da tarefa informada está válida
    public boolean validateTaskTime(Task task){
        if (task.getStartTime().isAfter(task.getEndTime())){
            throw new InvalidTaskDataException("the date of task is invalid. please check.");
        }else if (task.getStartTime().isEqual(task.getEndTime())){
            throw new InvalidTaskDataException("seu burro, tarefa na mesma hora que começa termina?");
        }
        return true;
    }
}
