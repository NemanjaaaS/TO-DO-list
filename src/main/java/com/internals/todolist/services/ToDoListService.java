package com.internals.todolist.services;

import com.internals.todolist.model.Task;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ToDoListService {
    List<Task> getAllTasks();

    Task saveTask(Task task);

    Optional<Task> getTaskById(Long id);

    Optional<Task> editTask(Task newTask, Long id);

    Optional<Task> setFinished(Long id);

    String deleteTask(Long id) throws Exception;

    List<Task> getByDate(LocalDate date);
}
