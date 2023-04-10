package com.internals.todolist.controller;

import com.internals.todolist.model.Task;
import com.internals.todolist.model.ToDoTasks;
import com.internals.todolist.repositories.TaskRepository;
import com.internals.todolist.repositories.ToDoTasksRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {
    private final ToDoTasksRepository toDoTasksRepository;
    private final TaskRepository taskRepository;

    public TaskController(ToDoTasksRepository toDoTasksRepository, TaskRepository taskRepository) {
        this.toDoTasksRepository = toDoTasksRepository;
        this.taskRepository = taskRepository;
    }

    @PostMapping("/toDoTasks")
    ToDoTasks newTaskList(@RequestBody ToDoTasks toDoTasks){
        return toDoTasksRepository.save(toDoTasks);
    }

    @PostMapping("/task")
    Task newTask(@RequestBody Task task){
        return taskRepository.save(task);
    }


    @GetMapping("/tasks")
    List<Task> getAllTasks(){
        return taskRepository.findAll();
    }
}
