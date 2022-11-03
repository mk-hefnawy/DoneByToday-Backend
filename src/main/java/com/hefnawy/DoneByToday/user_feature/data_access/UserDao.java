package com.hefnawy.DoneByToday.user_feature.data_access;

import com.hefnawy.DoneByToday.todo_feature.data_objects.Todo;
import com.hefnawy.DoneByToday.user_feature.data_objects.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.userName = ?1")
    User findByUserName(String userName);

    @Query("SELECT u.todos FROM User u WHERE u.userName = ?1")
    List<Todo> getTodosByUserName(String userName);

}
