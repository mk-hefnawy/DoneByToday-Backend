package com.hefnawy.DoneByToday.todo_feature.services;

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
public class TodoServiceImpl implements TodoService{
    @Autowired
    TodoDao todoDao;

    @Autowired
    UserDao userDao;

    @Override
    @Transactional
    public void updateTodo(String userName, Todo todo) throws NoSuchUserException, NoSuchTodoException {
        User user = userDao.findByUserName(userName);
        if (user == null){
            throw new NoSuchUserException("No Such User, Idiot");
        }else {
            Optional<Todo> checkTodo = todoDao.findById(todo.getId());
            if (checkTodo.isEmpty())  throw new NoSuchTodoException("No Such Todo, Idiot");
            else {
                todoDao.updateTodo(todo.getName(), todo.getDueDate(), todo.getDueTime(), todo.getId());
            }
        }
    }

    @Override
    public List<Todo> getAllTodosByUserName(String userName) throws NoSuchUserException {
        User user = userDao.findByUserName(userName);
        if (user == null){
            throw new NoSuchUserException("No Such User, Idiot");
        }else {
            return userDao.getTodosByUserName(userName);
        }
    }

    @Override
    @Transactional
    public Boolean addNewTodo(String userName, Todo todo) {
        User user = userDao.findByUserName(userName);
        if (user == null){
            return false;
        }else {
            List<Todo> currentTodos = user.getTodos();
            currentTodos.add(todo);
            return true;
        }
    }
}
