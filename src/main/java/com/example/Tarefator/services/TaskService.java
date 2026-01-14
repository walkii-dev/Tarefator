package com.example.Tarefator.services;

import com.example.Tarefator.mappers.TaskMapper;
import com.example.Tarefator.models.Task;
import com.example.Tarefator.repositories.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.example.Tarefator.dtos.TaskDTO;

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
        repository.save(convertedTask);
        return convertedTask;
    }
}
