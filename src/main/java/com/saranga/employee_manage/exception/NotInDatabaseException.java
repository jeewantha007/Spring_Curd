package com.saranga.employee_manage.exception;

public class NotInDatabaseException extends RuntimeException{
    public NotInDatabaseException(String message) {
        super(message);
    }
}
