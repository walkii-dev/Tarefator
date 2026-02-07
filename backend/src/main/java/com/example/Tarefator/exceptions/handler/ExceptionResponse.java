package com.example.Tarefator.exceptions.handler;

import java.util.Date;

public record ExceptionResponse(Date timestamp,String message, String details) {
}
