package com.internals.todolist.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue
    private Long id;
    private boolean finished;
    private String title;
    private String description;
    private LocalTime time;
    @ManyToOne
    private ToDoTasks toDoTasks;


    public Task() {

    }
}
