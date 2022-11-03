package com.hefnawy.DoneByToday.todo_feature.data_objects;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private LocalDate dueDate;
    private LocalTime dueTime;
}
