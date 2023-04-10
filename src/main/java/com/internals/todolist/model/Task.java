package com.internals.todolist.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean finished;
    private String title;
    private String description;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="HH:mm")
    private LocalTime time;
    @ManyToOne
    private ToDoTasks toDoTasks;


    public Task() {

    }
}
