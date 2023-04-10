package com.internals.todolist.repositories;

import com.internals.todolist.model.ToDoTasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoTasksRepository extends JpaRepository<ToDoTasks,Long> {

}
