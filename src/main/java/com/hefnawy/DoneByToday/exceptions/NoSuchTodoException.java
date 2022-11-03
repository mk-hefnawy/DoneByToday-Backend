package com.hefnawy.DoneByToday.exceptions;

public class NoSuchTodoException extends RuntimeException{
    public NoSuchTodoException(String message) {
        super(message);
    }
}
