package com.hefnawy.DoneByToday.todo_feature_test;

import com.hefnawy.DoneByToday.todo_feature.data_objects.Todo;
import com.hefnawy.DoneByToday.todo_feature.service.TodoServiceImpl;
import com.hefnawy.DoneByToday.user_feature.data_access.UserDao;
import com.hefnawy.DoneByToday.user_feature.data_objects.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TodoServiceImplTest {
    @Autowired
    TodoServiceImpl service;

    // Mocking the Class
    @Mock
    UserDao dao;

    @Test
    public void getAllTodos_UnRegisteredUser_Test(){
        // Mocking the method in the Mocked Class
        User user = new User();
        user.setUserName("UserName");
        //Mockito.when(dao.findByUserName("UnRegistered")).thenReturn(user);
        Mockito.when(dao.getTodosByUserName("UnRegistered")).thenReturn(List.of(new Todo()));

        //Mockito.doReturn("Hello").when(dao).test();
        Assertions.assertThat(service.getAllTodosByUserName("UnRegistered")).isEqualTo(List.of(new Todo()));

    }
}
