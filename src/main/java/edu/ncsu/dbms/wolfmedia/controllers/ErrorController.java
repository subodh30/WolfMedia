package edu.ncsu.dbms.wolfmedia.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler({Exception.class, SQLException.class, RuntimeException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleException(Exception e) {
        Map<String, Object> response = new HashMap<>();
        response.put("Query", e.getMessage());
        response.put("Error Details", e.getCause());
        return response;
    }


}
