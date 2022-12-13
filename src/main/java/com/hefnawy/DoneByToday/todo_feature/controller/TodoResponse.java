package com.hefnawy.DoneByToday.todo_feature.controller;

import com.hefnawy.DoneByToday.todo_feature.data_objects.Todo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class TodoResponse {
    private Todo todo;
    private String message;
    private String status;

}
