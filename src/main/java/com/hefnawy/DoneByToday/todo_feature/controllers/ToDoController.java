package com.hefnawy.DoneByToday.todo_feature.controllers;

import com.hefnawy.DoneByToday.exceptions.NoSuchTodoException;
import com.hefnawy.DoneByToday.exceptions.NoSuchUserException;
import com.hefnawy.DoneByToday.todo_feature.data_objects.Todo;
import com.hefnawy.DoneByToday.todo_feature.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/todo")
public class ToDoController {

    @Autowired
    TodoService todoService;

    @GetMapping("{userName}/all")
    public List<Todo> getAllToDos(@PathVariable("userName") String userName) {
        try {
            return todoService.getAllTodosByUserName(userName);
        }catch (NoSuchUserException e){
            throw e;
        }
    }

    @PostMapping("{userName}/add")
    public String addNewTodo(@RequestBody Todo todo, @PathVariable("userName") String userName) {
        if (todoService.addNewTodo(userName, todo)) {
            return "Done";
        } else return "Failed";
    }

    @PutMapping("{userName}/update")
    public String updateTodo(@RequestBody Todo todo, @PathVariable("userName") String userName) {
        try {
            todoService.updateTodo(userName, todo);
            return "Update is Done";
        }catch (NoSuchUserException | NoSuchTodoException u){
            throw u;
        }
    }
}