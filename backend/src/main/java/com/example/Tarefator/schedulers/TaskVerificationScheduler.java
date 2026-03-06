package com.example.Tarefator.schedulers;

import com.example.Tarefator.models.Task;
import com.example.Tarefator.models.TaskStatus;
import com.example.Tarefator.repositories.TaskRepository;
import com.example.Tarefator.services.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableScheduling
public class TaskVerificationScheduler {

    final TaskRepository repository;
    final TaskService service;

    public TaskVerificationScheduler(TaskRepository repository,TaskService service){
        this.repository = repository;
        this.service = service;
    }

    Logger logger = LoggerFactory.getLogger(TaskVerificationScheduler.class.getName());

    @Scheduled(fixedRate = 60000)
    public void verifyExpiredTasks(){
        logger.info("checking expired tasks...");

        List<Task> allTasks = repository.findByStatus(TaskStatus.CURRENT); // checar tarefas por status
        for (Task t : allTasks){
            service.checkExpiredTasks(t);
            // refatorar para somente as tarefas que estão em andamento, diminuindo o número de queries.
        }
    }
    @Scheduled(fixedRate = 60001)
    public void verifyCurrentTasks(){
        logger.info("looking for current tasks...");

        List<Task> allTasks = repository.findByStatus(TaskStatus.CREATED); // checar tarefas por status
        for (Task t : allTasks){
            service.checkCurrentTasks(t);
        }
    }





}
