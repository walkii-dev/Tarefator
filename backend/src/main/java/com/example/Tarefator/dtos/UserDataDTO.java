package com.example.Tarefator.dtos;

import com.example.Tarefator.models.AppUser;
import com.example.Tarefator.models.AppUserRole;

import java.util.List;

public record UserDataDTO(Long userId,
                          String userFullName,
                          String userEmail,
                          AppUserRole userRole,
                          List<TaskDTO> userTasks) {
    public UserDataDTO (AppUser user){
        this(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getUserRole(),
                user.getUserTasks().stream().map(TaskDTO::new).toList());
    }
}
