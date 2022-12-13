package com.hefnawy.DoneByToday.todo_feature.service;

import com.hefnawy.DoneByToday.exceptions.NoSuchTodoException;
import com.hefnawy.DoneByToday.exceptions.NoSuchUserException;
import com.hefnawy.DoneByToday.todo_feature.data_objects.Todo;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
public interface TodoService {
    List<Todo> getAllTodosByUserName(String userName) throws NoSuchUserException;
    Todo addNewTodo(String userName, Todo todo) throws NoSuchUserException;
    Todo updateTodo(String userName, String todoId, Todo todo) throws NoSuchUserException, NoSuchTodoException;
    Todo deleteTodo(String userName, String todoId) throws NoSuchUserException, NoSuchTodoException;
}
