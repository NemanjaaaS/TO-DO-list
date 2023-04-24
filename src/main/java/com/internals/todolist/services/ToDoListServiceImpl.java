package com.internals.todolist.services;

import com.internals.todolist.exceptions.NoTaskException;
import com.internals.todolist.model.Task;
import com.internals.todolist.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoListServiceImpl implements ToDoListService{

    private final TaskRepository taskRepository;

    public ToDoListServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    @Override
    public Task saveTask(Task task){
        return taskRepository.save(task);
    }

    @Override
    public Optional<Task> getTaskById(Long id){
        return taskRepository.findById(id);
    }

    @Override
    public Optional<Task> editTask(Task newTask,Long id){
        return taskRepository.findById(id)
                .map(task ->{
                    task.setTitle(newTask.getTitle());
                    task.setDescription(newTask.getDescription());
                    task.setFinished(newTask.isFinished());
                    task.setTime(newTask.getTime());
                    return taskRepository.save(task);
                });
    }

    @Override
    public Optional<Task> setFinished(Long id){
        return taskRepository.findById(id)
                .map(task1 -> {
                    task1.setFinished(true);
                    return taskRepository.save(task1);
                });
    }
    @Override
    public String deleteTask(Long id) throws NoTaskException {
        if(!taskRepository.existsById(id)){
            throw new NoTaskException();
        }
        taskRepository.deleteById(id);
        return "Task deleted";
    }

    @Override
    public List<Task> getByDate(LocalDate date){
        return taskRepository.getTasksByDate(date);
    }



}
