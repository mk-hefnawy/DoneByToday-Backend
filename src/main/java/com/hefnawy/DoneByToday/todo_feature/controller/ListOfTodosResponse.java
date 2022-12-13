package com.hefnawy.DoneByToday.todo_feature.controller;

import com.hefnawy.DoneByToday.todo_feature.data_objects.Todo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class ListOfTodosResponse {
    private List<Todo> todos;
    private String message;
    private String status;
}
