package com.example.Tarefator.unitytests.services;

import com.example.Tarefator.dtos.TaskDTO;
import com.example.Tarefator.exceptions.InvalidTaskDataException;
import com.example.Tarefator.services.TaskService;
import com.example.Tarefator.unitytests.mappers.mocks.MockTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)


public class TaskServiceTest {

    MockTask input;

    @InjectMocks
    TaskService service;

    @BeforeEach
    public void setUp(){
        input = new MockTask();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAndLaunchExceptionWithSameStartAndEnd(){

        TaskDTO dto = input.mockDTO(1);
        dto.setEndTime(dto.getEndTime().minusSeconds(1));

        InvalidTaskDataException exception = assertThrows(InvalidTaskDataException.class,
                () -> service.createTask(dto));

        assertEquals("uma tarefa não pode começar e encerrar ao mesmo tempo.",exception.getMessage());
    }
    @Test
    void createAndLaunchExceptionWithStartAfterEnd(){

        TaskDTO dto = input.mockDTO(1);
        dto.setEndTime(dto.getStartTime().minusSeconds(1));

        InvalidTaskDataException exception = assertThrows(InvalidTaskDataException.class,
                () -> service.createTask(dto));

        assertEquals("a data de início não pode ser após a data de fim da tarefa.",exception.getMessage());
    }
    @Test
    void createAndLaunchExceptionWithSameStartInPast(){

        TaskDTO dto = input.mockDTO(1);
        var actualTime = LocalDateTime.ofInstant(Instant.now(),ZoneId.systemDefault());
        dto.setStartTime(actualTime.minusSeconds(1));

        InvalidTaskDataException exception = assertThrows(InvalidTaskDataException.class,
                () -> service.createTask(dto));

        assertEquals("uma tarefa não pode começar no passado.",exception.getMessage());
    }

}
