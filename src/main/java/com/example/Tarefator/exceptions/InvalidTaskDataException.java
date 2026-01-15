package com.example.Tarefator.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidTaskDataException extends RuntimeException {
    public InvalidTaskDataException(String message) {
        super(message);
    }
}
