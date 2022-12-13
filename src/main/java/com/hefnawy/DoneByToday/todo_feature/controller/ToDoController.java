package com.hefnawy.DoneByToday.todo_feature.controller;

import com.hefnawy.DoneByToday.exceptions.NoSuchTodoException;
import com.hefnawy.DoneByToday.exceptions.NoSuchUserException;
import com.hefnawy.DoneByToday.security.JwtUtils;
import com.hefnawy.DoneByToday.todo_feature.data_objects.Todo;
import com.hefnawy.DoneByToday.todo_feature.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/todos")
public class ToDoController {
    @Autowired
    TodoService todoService;

    @GetMapping("")
    public ListOfTodosResponse getAllToDos(@RequestHeader(value = "Authorization") String authorization) {
        String userName = new JwtUtils().getUserNameFromToken(authorization.substring("Bearer ".length()));
        try {
            return new ListOfTodosResponse(todoService.getAllTodosByUserName(userName), null, "SUCCESS");
        }catch (NoSuchUserException e){
            return new ListOfTodosResponse(null, e.getMessage(), "FAILURE");
        }
    }

    @PostMapping("")
    public TodoResponse addNewTodo(@RequestBody Todo todo, @RequestHeader(value = "Authorization") String authorization) {
        String userName = new JwtUtils().getUserNameFromToken(authorization.substring("Bearer ".length()));

        try {
            return new TodoResponse(todoService.addNewTodo(userName, todo), null, "SUCCESS");
        }catch (NoSuchUserException e){
            return new TodoResponse(null, e.getMessage(), "FAILURE");
        }

    }

    @PutMapping("/{todoId}")
    public TodoResponse updateTodo(@RequestHeader(value = "Authorization") String authorization,
                             @PathVariable("todoId") String todoId,
                             @RequestBody Todo todo) {
        String userName = new JwtUtils().getUserNameFromToken(authorization.substring("Bearer ".length()));
        try {
            return new TodoResponse(todoService.updateTodo(userName, todoId, todo), null, "SUCCESS");
        }catch (NoSuchUserException | NoSuchTodoException e){
            return new TodoResponse(null, e.getMessage(), "FAILURE");
        }
    }

    @DeleteMapping("/{todoId}")
    public TodoResponse deleteTodo(@RequestHeader(value = "Authorization") String authorization,
                                   @PathVariable("todoId") String todoId) {
        String userName = new JwtUtils().getUserNameFromToken(authorization.substring("Bearer ".length()));
        try {
            return new TodoResponse(todoService.deleteTodo(userName, todoId), null, "SUCCESS");
        }catch (NoSuchUserException | NoSuchTodoException e){
            return new TodoResponse(null, e.getMessage(), "FAILURE");
        }
    }
}