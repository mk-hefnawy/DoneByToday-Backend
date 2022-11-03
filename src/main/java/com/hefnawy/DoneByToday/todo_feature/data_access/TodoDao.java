package com.hefnawy.DoneByToday.todo_feature.data_access;

import com.hefnawy.DoneByToday.todo_feature.data_objects.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface TodoDao extends JpaRepository<Todo, Integer> {

    @Modifying
    @Query("UPDATE Todo set " +
            "name = :name" + "," +
            "dueDate = :dueDate" +  "," +
            "dueTime = :dueTime WHERE id = :todoId")
    void updateTodo(@Param("name") String name,
                       @Param("dueDate") LocalDate dueDate,
                       @Param("dueTime") LocalTime dueTime,
                       @Param("todoId") Integer todoId);
}
