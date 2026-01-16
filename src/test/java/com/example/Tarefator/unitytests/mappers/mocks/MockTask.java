package com.example.Tarefator.unitytests.mappers.mocks;

import com.example.Tarefator.dtos.TaskDTO;
import com.example.Tarefator.models.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MockTask {
    public Task mockEntity(){
        return mockEntity(0);
    }

    public TaskDTO mockDTO(){
        return mockDTO(0);
    }

    /*
    Obs.: a criação do mock é de uma tarefa válida no banco de dados, levando em conta o momento da criação e
    adicionando apenas um segundo.
     */

    public Task mockEntity(int number){
        Task task = new Task();
        task.setTitle("Title test "+number);
        task.setDescription("Description test "+number);
        task.setStartTime(LocalDateTime.now());
        task.setEndTime(LocalDateTime.now().plusSeconds(1));
        return task;
    }
    public TaskDTO mockDTO(int number){
        TaskDTO dto = new TaskDTO();
        dto.setTitle("Tile test "+number);
        dto.setDescription("Description test "+number);
        dto.setStartTime(LocalDateTime.now());
        dto.setEndTime(LocalDateTime.now().plusSeconds(1));
        return dto;
    }

    public List<Task> mockTaskList(){
        List<Task> mockList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mockList.add(mockEntity(i));
        }
        return mockList;
    }

    public List<TaskDTO> mockDtoList(){
        List<TaskDTO> mockDtoList = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            mockDtoList.add(mockDTO(i));
        }
        return mockDtoList;
    }
}
