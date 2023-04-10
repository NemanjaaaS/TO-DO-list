package com.internals.todolist.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Entity
@Data
@AllArgsConstructor
public class ToDoTasks {

    @Id
    @GeneratedValue
    private Long id;
    private Date date;
    @OneToMany
    private List<Task> tasks;

    public ToDoTasks() {

    }
}
