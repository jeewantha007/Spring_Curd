package com.saranga.employee_manage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandler{

    @org.springframework.web.bind.annotation.ExceptionHandler(AlreadyExistException.class)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<String> handleAlreadyExistException(AlreadyExistException e){
          return new ResponseEntity<>("Already Exist "+ e.getMessage(), HttpStatus.FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NotInDatabaseException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNotInDatabaseException(NotInDatabaseException e){
        return new ResponseEntity<>("Not in database "+ e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
