package com.example.Tarefator.services;

import com.example.Tarefator.configurations.security.TokenToolService;
import com.example.Tarefator.exceptions.InvalidTaskDataException;
import com.example.Tarefator.exceptions.ResourceNotFoundException;
import com.example.Tarefator.models.AppUser;
import com.example.Tarefator.models.Task;
import com.example.Tarefator.models.TaskStatus;
import com.example.Tarefator.repositories.AppUserRepository;
import com.example.Tarefator.repositories.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.example.Tarefator.dtos.TaskDTO;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    final TaskRepository repository;

    final AppUserRepository userRepository;

    public TaskService (TaskRepository repository, AppUserRepository userRepository){
        this.repository = repository;
        this.userRepository = userRepository;
    }

    final LocalDateTime actualServerTime = LocalDateTime.ofInstant(Instant.now(),ZoneId.systemDefault());

    Logger logger = LoggerFactory.getLogger(TaskService.class.getName());

    public Task createTask (TaskDTO taskData, Authentication authentication){
        logger.info("mapping dto to entity and saving in database.");
        try{
            validateTaskTime(taskData);
            // futuramente aqui terá uma verificação de tarefa duplicata.
        } catch (InvalidTaskDataException ex) {
            throw new InvalidTaskDataException(ex.getMessage());
        }

        //verificar isso pois não está recuperando o login do token.
        logger.info("looking for owner of task...");
        String username = authentication.getCredentials().toString();
        System.out.println(username);
        AppUser owner = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Usuário do Token não encontrado."));

        Task convertedTask = new Task(taskData,owner);

        repository.save(convertedTask);
        return convertedTask;
    }


    public TaskDTO getSimpleTask(UUID id) throws ResourceNotFoundException{
        logger.info("finding a unique task saved on database.");
        var task = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("tarefa não encontrada no banco de dados."));
        return new TaskDTO(task);
    }

    public List<Task> getAllTasks() {
        logger.info("finding for all tasks saved in database.");
        var tasksList = repository.findAll();
        return tasksList;
        //as tarefas precisam estar em ordem(mais recentes primeiro) e não pode aparecer as tarefas que foram canceladas.
    }

    public Task editTask(TaskDTO editedTask) {
        var taskToEdit = repository.getReferenceById(editedTask.getId());
        taskToEdit.setStatus(TaskStatus.EDITED);
        taskToEdit.setTitle(editedTask.getTitle());
        taskToEdit.setDescription(editedTask.getDescription());
        taskToEdit.setStartTime(editedTask.getStartTime());
        taskToEdit.setEndTime(editedTask.getEndTime());
        repository.save(taskToEdit);
        return taskToEdit;
    }

    public Task cancelTask(UUID id) {
        var task = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("tarefa não encontrada no banco de dados."));
        task.setStatus(TaskStatus.CANCELLED);
        // depois de um certo tempo ela é eliminada de vez do banco de dados.
        //obs.: ela cancelada não pode aparecer na listagem de todas as tarefas
        return repository.save(task);
    }

    // função que valida se a data da tarefa informada está válida
    public boolean validateTaskTime(TaskDTO dto){
        return switch (dto) {
            case TaskDTO d when d.getStartTime().isAfter(d.getEndTime()) ->
                    throw new InvalidTaskDataException("a data de início não pode ser após a data de fim da tarefa.");

            case TaskDTO d when d.getStartTime().isEqual(d.getEndTime()) ->
                    throw new InvalidTaskDataException("uma tarefa não pode começar e encerrar ao mesmo tempo.");

            case TaskDTO d when d.getStartTime().isBefore(LocalDateTime.ofInstant(Instant.now(),ZoneId.systemDefault())) ->
                    throw new InvalidTaskDataException("uma tarefa não pode começar no passado.");
            case null, default -> true;
        };
    }

    //função que expira uma tarefa quando ela passa do tempo.
    public Task checkExpiredTasks(Task task){

        if (task.getEndTime().isBefore(actualServerTime)){
            task.setStatus(TaskStatus.EXPIRED);
            repository.save(task);
        }
        return task;
    }

    public TaskDTO markTaskAsDone(UUID id) {
        var task = repository.findById(id).get();
        task.setStatus(TaskStatus.DONE);
        repository.save(task);
        logger.info("tarefa "+task.getTitle()+" concluída!");
        return new TaskDTO(task);
    }


    //função que valida se a tarefa está sendo sobreposta (provável feature)
}
