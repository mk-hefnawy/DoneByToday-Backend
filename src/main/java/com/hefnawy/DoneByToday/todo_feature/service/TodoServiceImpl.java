package com.hefnawy.DoneByToday.todo_feature.service;

import com.hefnawy.DoneByToday.exceptions.NoSuchTodoException;
import com.hefnawy.DoneByToday.exceptions.NoSuchUserException;
import com.hefnawy.DoneByToday.todo_feature.data_access.TodoDao;
import com.hefnawy.DoneByToday.todo_feature.data_objects.Todo;
import com.hefnawy.DoneByToday.user_feature.data_access.UserDao;
import com.hefnawy.DoneByToday.user_feature.data_objects.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {
    @Autowired
    TodoDao todoDao;

    @Autowired
    UserDao userDao;

    @Override
    @Transactional
    public Todo deleteTodo(String userName, String todoId) throws NoSuchUserException, NoSuchTodoException {
        Optional<User> userOptional = userDao.findByUserName(userName);
        userOptional.orElseThrow(() -> new NoSuchUserException("No Such User, Idiot"));

        Optional<Todo> checkTodo = userOptional.get().getTodos()
                .stream().filter(todo -> todo.getId() == Integer.parseInt(todoId)).findFirst();

        if (checkTodo.isEmpty()) throw new NoSuchTodoException("No Such Todo, Idiot");
        else {
            userOptional.get().getTodos().remove(checkTodo.get());
            return checkTodo.get();
        }
    }

    @Override
    @Transactional
    public Todo updateTodo(String userName, String todoId, Todo todo) throws NoSuchUserException, NoSuchTodoException {
        Optional<User> userOptional = userDao.findByUserName(userName);
        userOptional.orElseThrow(() -> new NoSuchUserException("No Such User, Idiot"));

        Optional<Todo> checkTodo = userOptional.get().getTodos()
                .stream().filter(theTodo -> theTodo.getId() == Integer.parseInt(todoId)).findFirst();

        if (checkTodo.isEmpty()) throw new NoSuchTodoException("No Such Todo, Idiot");
        else {

            checkTodo.get().setName(todo.getName());
            checkTodo.get().setDueDate(todo.getDueDate());
            checkTodo.get().setDueTime(todo.getDueTime());
            return checkTodo.get();

        }
    }

    @Override
    public List<Todo> getAllTodosByUserName(String userName) throws NoSuchUserException {
        Optional<User> userOptional = userDao.findByUserName(userName);
        userOptional.orElseThrow(() -> new NoSuchUserException("No Such User, Idiot"));
        return userDao.getTodosByUserName(userName);

    }

    @Override
    @Transactional
    public Todo addNewTodo(String userName, Todo todo) throws NoSuchUserException{
        Optional<User> userOptional = userDao.findByUserName(userName);
        userOptional.orElseThrow(() -> new NoSuchUserException("No Such User, Idiot"));

        List<Todo> currentTodos = userOptional.get().getTodos();
        currentTodos.add(todo);
        return todo;

    }
}
