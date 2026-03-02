package com.example.Tarefator.dtos;

import com.example.Tarefator.models.AppUser;
import com.example.Tarefator.models.AppUserRole;

import java.util.List;

public record UserDataDTO(Long id,
                          String fullname,
                          String email,
                          AppUserRole role,
                          List<TaskDTO> tasks) {
    public UserDataDTO (AppUser user){
        this(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getUserRole(),
                user.getUserTasks().stream().map(TaskDTO::new).toList());
    }
}
