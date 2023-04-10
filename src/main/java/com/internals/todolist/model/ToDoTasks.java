package com.internals.todolist.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date date;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Task> tasks;

    public ToDoTasks() {

    }
}
