package com.internals.todolist.controller;

import com.internals.todolist.model.Task;
import com.internals.todolist.repositories.TaskRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PostMapping("/task")
    Task newTask(@RequestBody Task task){
        return taskRepository.save(task);
    }
    @GetMapping("/task/{id}")
    Optional<Task> getTaskById(@PathVariable Long id){
        return taskRepository.findById(id);
    }
    @PutMapping("/task/{id}")
    Optional<Task> editTask(@RequestBody Task newTask,@PathVariable Long id){

        return taskRepository.findById(id)
                .map(task ->{
                    task.setTitle(newTask.getTitle());
                    task.setDescription(newTask.getDescription());
                    task.setFinished(newTask.isFinished());
                    task.setTime(newTask.getTime());
                    return taskRepository.save(task);
                });
    }
    @DeleteMapping("/task/{id}")
    String deleteTask(@PathVariable Long id) throws Exception {
        if(!taskRepository.existsById(id)){
            throw new Exception("Task doesn't exist");
        }
        taskRepository.deleteById(id);
        return "Task deleted";
    }
    @GetMapping("/tasksDate/{date}")
    List<Task> getAllByDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
        return taskRepository.getTasksByDate(date);
    }
    @GetMapping("/tasks")
    List<Task> getAllTasks(){
        return taskRepository.findAll();
    }
}
