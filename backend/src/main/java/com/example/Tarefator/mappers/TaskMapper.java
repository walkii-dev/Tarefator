package com.example.Tarefator.mappers;

import com.example.Tarefator.dtos.TaskDTO;
import com.example.Tarefator.models.Task;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    TaskDTO toTaskDTO(Task task);
    Task toTaskEntity(TaskDTO taskDTO);

    List<TaskDTO> toTaskList(List<Task> entityList);
}
