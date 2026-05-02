package com.example.Tarefator.services;

import com.example.Tarefator.dtos.TaskDataDTO;
import com.example.Tarefator.exceptions.InvalidTaskDataException;
import com.example.Tarefator.exceptions.ResourceNotFoundException;
import com.example.Tarefator.models.AppUser;
import com.example.Tarefator.models.Task;
import com.example.Tarefator.models.TaskStatus;
import com.example.Tarefator.repositories.AppUserRepository;
import com.example.Tarefator.repositories.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Service
public class TaskService {

    final TaskRepository repository;

    final AppUserRepository userRepository;

    public TaskService (TaskRepository repository, AppUserRepository userRepository){
        this.repository = repository;
        this.userRepository = userRepository;
    }

    final LocalDateTime actualServerTime = LocalDateTime.ofInstant(Instant.now(),ZoneId.of("America/Sao_Paulo"));

    Logger logger = LoggerFactory.getLogger(TaskService.class.getName());

    @Transactional
    public Task createTask (TaskDataDTO taskData, Authentication authentication){
        logger.info("mapping dto to entity and saving in database.");
        try{
            validateTaskTime(taskData);
            // futuramente aqui terá uma verificação de tarefa duplicata.
        } catch (InvalidTaskDataException ex) {
            throw new InvalidTaskDataException(ex.getMessage());
        }

        //verificar isso pois não está recuperando o login do token.
        logger.info("looking for owner of task...");
        var username = authentication.getName();
        AppUser owner = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Usuário do Token não encontrado."));

        Task convertedTask = new Task(taskData,owner);

        repository.save(convertedTask);
        return convertedTask;
    }


    public TaskDataDTO getSimpleTask(UUID id) throws ResourceNotFoundException{
        logger.info("finding a unique task saved on database.");
        var task = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("tarefa não encontrada no banco de dados."));
        return new TaskDataDTO(task);
    }

    public Page<TaskDataDTO> getAllTasks(TaskStatus status, int page, int size) {
        logger.info("finding for all tasks saved in database.");
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC,"startTime");
        return new PageImpl<>(repository.findByStatus(status,pageRequest).stream().map(TaskDataDTO::new).toList());
        //as tarefas precisam estar em ordem(mais recentes primeiro) e não pode aparecer as tarefas que foram canceladas.
    }

    @Transactional
    public Task editTask(TaskDataDTO editedTask) {
        var taskToEdit = repository.getReferenceById(editedTask.id());
        taskToEdit.setStatus(TaskStatus.EDITED);
        taskToEdit.setTitle(editedTask.title());
        taskToEdit.setDescription(editedTask.description());
        taskToEdit.setStartTime(editedTask.startTime());
        taskToEdit.setEndTime(editedTask.endTime());
        repository.save(taskToEdit);
        return taskToEdit;
    }

    @Transactional
    public Task cancelTask(UUID id) {
        var task = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("task not found in database. please verify the task code."));
        task.setStatus(TaskStatus.CANCELLED);
        return repository.save(task);
    }

    // função que valida se a data da tarefa informada está válida
    public boolean validateTaskTime(TaskDataDTO dto){
        return switch (dto) {
            case TaskDataDTO d when d.startTime().isAfter(d.endTime()) ->
                    throw new InvalidTaskDataException("a data de início não pode ser após a data de fim da tarefa.");

            case TaskDataDTO d when d.startTime().isEqual(d.endTime()) ->
                    throw new InvalidTaskDataException("uma tarefa não pode começar e encerrar ao mesmo tempo.");

            case TaskDataDTO d when d.startTime().isBefore(LocalDateTime.ofInstant(Instant.now(),ZoneId.systemDefault())) ->
                    throw new InvalidTaskDataException("uma tarefa não pode começar no passado.");
            case null, default -> true;
        };
    }

    //função que expira uma tarefa quando ela passa do tempo.
    public Task checkExpiredTasks(Task task){

        if ( task.getEndTime().isEqual(actualServerTime)||task.getEndTime().isBefore(actualServerTime) ){
            task.setStatus(TaskStatus.EXPIRED);
            repository.save(task);
        }
        return task;
    }
    // função que verifica as tarefas que foram criadas pra ver se estão em andamento.
    public Task checkCurrentTasks(Task task){

        if (task.getStartTime().isEqual(actualServerTime) || task.getStartTime().isBefore(actualServerTime)){
            task.setStatus(TaskStatus.CURRENT);
            repository.save(task);
        }
        return task;
    }


    @Transactional
    public TaskDataDTO markTaskAsDone(UUID id) {
        var task = repository.findById(id).get();
        task.setStatus(TaskStatus.DONE);
        repository.save(task);
        logger.info("task "+task.getId()+" is done!");
        return new TaskDataDTO(task);
    }

    //um mesmo usuário não pode ter duas tarefas que estejam sendo feitas ao mesmo tempo.
    //função que valida se a tarefa está sendo sobreposta (provável feature)
}
