package com.hefnawy.DoneByToday.user_feature.data_objects;

import com.hefnawy.DoneByToday.todo_feature.data_objects.Todo;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")

@Getter
@Setter
public class User {
    @GeneratedValue
    @Id
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    @OneToMany
    @Cascade(value = org.hibernate.annotations.CascadeType.MERGE)
    private List<Todo> todos;

}
