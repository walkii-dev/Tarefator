package com.example.Tarefator.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AuthRegisterDTO(@NotNull(message = "user have to be a name.")
                              String fullname, //futuramente deverá negar número.

                              @NotNull(message = "email is required.")
                              String email, // futuramente deverá ter um regex de e-mail.

                              @NotNull(message = "password is required.")
                              String password)
{}
