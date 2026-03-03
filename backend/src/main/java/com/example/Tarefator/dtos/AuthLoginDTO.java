package com.example.Tarefator.dtos;

import jakarta.validation.constraints.NotBlank;

public record AuthLoginDTO(@NotBlank(message = "cannot have a empty email") String email,
                           @NotBlank(message = "password is required.") String password) {
}
