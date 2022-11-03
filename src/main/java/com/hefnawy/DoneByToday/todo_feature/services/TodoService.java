package com.hefnawy.DoneByToday.todo_feature.services;

import com.hefnawy.DoneByToday.exceptions.NoSuchTodoException;
import com.hefnawy.DoneByToday.exceptions.NoSuchUserException;
import com.hefnawy.DoneByToday.todo_feature.data_objects.Todo;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
public interface TodoService {
    List<Todo> getAllTodosByUserName(String userName) throws NoSuchUserException;
    Boolean addNewTodo(String userName, Todo todo);
    void updateTodo(String userName, Todo todo) throws NoSuchUserException, NoSuchTodoException;
}
