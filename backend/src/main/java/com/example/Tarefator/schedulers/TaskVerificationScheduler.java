package com.example.Tarefator.schedulers;

import com.example.Tarefator.models.Task;
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

        List<Task> allTasks = repository.findAll(); // checar tarefas por status
        for (Task t : allTasks){
            service.checkExpiredTasks(t);
            // refatorar para somente as tarefas que estão correntes, diminuindo o número de queries.
        }
    }





}
