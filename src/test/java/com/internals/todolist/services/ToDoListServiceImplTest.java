package com.internals.todolist.services;

import static org.junit.jupiter.api.Assertions.*;

import com.internals.todolist.exceptions.NoTaskException;
import com.internals.todolist.model.Task;
import com.internals.todolist.repositories.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class ToDoListServiceImplTest {

    @Mock
    private TaskRepository taskRepository;
    @InjectMocks
    private ToDoListServiceImpl toDoListService;

    private static Task task1;
    private static Task task2;

    @BeforeAll
    public static void setUp() {
        task1 = Task.builder().date(LocalDate.now()).id(1L).description("Neki opis").finished(false).title("Neki naslov").date(LocalDate.of(2023, 5, 6)).time(LocalTime.of(13, 30)).build();
        task2 = Task.builder().date(LocalDate.now()).id(2L).description("Neki drugi opis").finished(false).title("Neki drugi naslov").time(LocalTime.of(17, 35)).build();

    }

    @Test
    void getAllTasks() {
        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);

        given(taskRepository.findAll()).willReturn(taskList);
        assertNotNull(toDoListService.getAllTasks());
        assertEquals(taskList, toDoListService.getAllTasks());
    }

    @Test
    void saveTask() {
        Task newTask = new Task();
        newTask.setTitle("NewTask");
        newTask.setDescription("NewTaskDesc");
        newTask.setDate(LocalDate.of(2023, 11, 13));
        newTask.setFinished(false);
        newTask.setTime(LocalTime.of(13, 13));

        when(taskRepository.save(newTask)).thenReturn(newTask);

        assertEquals(newTask, toDoListService.saveTask(newTask));
    }

    @Test
    void getTaskById() {
        when(taskRepository.findById(1L)).thenReturn(Optional.ofNullable(task1));

        assertEquals(Optional.of(task1), toDoListService.getTaskById(1L));
    }

    @Test
    void editTask() {
        Task editedTask = Task.builder().date(LocalDate.now()).description("Editovan opis").finished(false).title("Editovan naslov").date(LocalDate.of(2055, 5, 6)).time(LocalTime.of(10, 00)).build();
        when(taskRepository.findById(1L)).thenReturn(Optional.ofNullable(task1));
        when(taskRepository.save(task1)).thenReturn(task1);
        assertEquals(Optional.of(task1), toDoListService.editTask(editedTask, 1L));

    }

    @Test
    void setFinished() {
        task1.setFinished(true);
        when(taskRepository.findById(1L)).thenReturn(Optional.ofNullable(task1));
        when(taskRepository.save(task1)).thenReturn(task1);
        assertEquals(Optional.of(task1).get().isFinished(), toDoListService.setFinished(1L).get().isFinished());
        assertTrue(toDoListService.setFinished(1L).get().isFinished());
    }

    @Test
    void deleteTask() throws NoTaskException {
        when(taskRepository.existsById(1L)).thenReturn(true);
        assertEquals("Task deleted",toDoListService.deleteTask(1L));
    }

    @Test
    void getByDate() {
        Task newTask = new Task();
        newTask.setTitle("NewTask");
        newTask.setDescription("NewTaskDesc");
        newTask.setDate(LocalDate.of(2023, 5, 6));
        newTask.setFinished(false);
        newTask.setTime(LocalTime.of(13, 13));

        List<Task> taskListDate = new ArrayList<>();
        taskListDate.add(task1);
        taskListDate.add(newTask);
        when(taskRepository.getTasksByDate(LocalDate.of(2023, 5, 6))).thenReturn(taskListDate);

        assertEquals(taskListDate, toDoListService.getByDate(LocalDate.of(2023, 5, 6)));

    }
}