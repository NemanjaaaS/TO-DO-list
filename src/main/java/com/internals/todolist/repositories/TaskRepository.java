package com.internals.todolist.repositories;

import com.internals.todolist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
    @Query("select t from Task t where t.date = :date")
    List<Task> getTasksByDate(@PathVariable("date") LocalDate date);
}
