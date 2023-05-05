package com.internals.todolist.controller;

import com.internals.todolist.model.Task;
import com.internals.todolist.repositories.TaskRepository;
import com.internals.todolist.services.ToDoListServiceImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000/")
public class TaskController {

    private final ToDoListServiceImpl toDoListService;

    public TaskController(ToDoListServiceImpl toDoListService) {

        this.toDoListService = toDoListService;
    }

    @PostMapping("/task")
    Task newTask(@RequestBody Task task){

        return toDoListService.saveTask(task);
    }
    @GetMapping("/task/{id}")
    Optional<Task> getTaskById(@PathVariable Long id){
        return toDoListService.getTaskById(id);
    }
    @PutMapping("/task/{id}")
    Optional<Task> editTask(@RequestBody Task newTask,@PathVariable Long id){
        return toDoListService.editTask(newTask,id);
    }
    @PostMapping("/task/{id}")
    Optional<Task> setFinished(@PathVariable Long id){
        return toDoListService.setFinished(id);
    }
    @DeleteMapping("/task/{id}")
    String deleteTask(@PathVariable Long id) throws Exception {
        return toDoListService.deleteTask(id);
    }
    @GetMapping("/tasksDate/{date}")
    List<Task> getAllByDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
        return toDoListService.getByDate(date);
    }
    @GetMapping("/tasks")
    List<Task> getAllTasks(){
        return toDoListService.getAllTasks();
    }
}
