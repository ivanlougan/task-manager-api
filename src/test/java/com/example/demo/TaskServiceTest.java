package com.example.demo;

import com.example.demo.dto.CreateTaskRequest;
import com.example.demo.dto.TaskResponse;
import com.example.demo.dto.UpdateTaskRequest;
import com.example.demo.exception.TaskNotFoundException;
import com.example.demo.model.Task;
import com.example.demo.model.User;
import com.example.demo.repository.TaskRepository;
import com.example.demo.security.CurrentUserService;
import com.example.demo.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;
    @Mock
    private CurrentUserService currentUserService;

    @InjectMocks
    private TaskService taskService;


    @Test
    void shouldCreateTask() {
        CreateTaskRequest request =
                new CreateTaskRequest("Learn JWT");

        User user = new User(
                "robert@example.com",
                "encoded-password"
        );

        when(currentUserService.getCurrentUser())
                .thenReturn(user);

        Task savedTask = new Task(
                "Learn JWT",
                false
        );

        savedTask.setUser(user);

        when(taskRepository.save(any(Task.class)))
                .thenReturn(savedTask);

        TaskResponse response =
                taskService.createTask(request);

        assertEquals("Learn JWT", savedTask.getTitle());
        assertEquals(user, savedTask.getUser());
        assertFalse(savedTask.isCompleted());

        verify(taskRepository).save(any(Task.class));
    }

    @Test
    void shouldReturnTaskById() {

        User user = new User(
                "robert@test.com",
                "password"
        );

        Task task = new Task(
                "Tests are great, I love tests",
                false
        );

        task.setId(1L);
        task.setUser(user);

        when(currentUserService.getCurrentUser())
                .thenReturn(user);

        when(taskRepository.findByIdAndUser(1L, user))
                .thenReturn(Optional.of(task));

        TaskResponse response = taskService.getTaskById(1L);

        assertEquals(1L, response.id());
        assertEquals("Tests are great, I love tests", response.title());

        verify(taskRepository).findByIdAndUser(1L, user);
    }

    @Test
    void shouldThrowExceptionWhenTaskNotFound() {

        User user = new User(
                "robert@test.com",
                "password"
        );

        when(currentUserService.getCurrentUser())
                .thenReturn(user);

        when(taskRepository.findByIdAndUser(1L, user))
                .thenReturn(Optional.empty());

        assertThrows(
                TaskNotFoundException.class,
                () -> taskService.getTaskById(1L)
        );

        verify(taskRepository).findByIdAndUser(1L, user);
    }

    @Test
    void shouldThrowExceptionWhenUpdatingMissingTask() {

        User user = new User(
                "robert@test.com",
                "password"
        );

        UpdateTaskRequest request =
                new UpdateTaskRequest(
                        "New title",
                        true
                );

        when(currentUserService.getCurrentUser())
                .thenReturn(user);

        when(taskRepository.findByIdAndUser(1L, user))
                .thenReturn(Optional.empty());

        assertThrows(
                TaskNotFoundException.class,
                () -> taskService.updateTask(1L, request)
        );

        verify(taskRepository).findByIdAndUser(1L, user);

        verify(taskRepository, never())
                .save(any(Task.class));
    }

    @Test
    void shouldDeleteTask() {

        User user = new User(
                "robert@test.com",
                "password"
        );

        Task task = new Task(
                "Delete me",
                false
        );

        task.setId(1L);

        when(currentUserService.getCurrentUser())
                .thenReturn(user);

        when(taskRepository.findByIdAndUser(1L, user))
                .thenReturn(Optional.of(task));

        taskService.deleteTaskById(1L);

        verify(taskRepository).delete(task);
    }

    @Test
    void shouldUpdateTask() {

        User user = new User(
                "robert@test.com",
                "password"
        );

        Task task = new Task(
                "Old title",
                false
        );

        task.setId(1L);

        UpdateTaskRequest request =
                new UpdateTaskRequest(
                        "New title",
                        true
                );

        when(currentUserService.getCurrentUser())
                .thenReturn(user);

        when(taskRepository.findByIdAndUser(1L, user))
                .thenReturn(Optional.of(task));

        when(taskRepository.save(any(Task.class)))
                .thenReturn(task);

        TaskResponse response = taskService.updateTask(1L, request);

        assertEquals("New title", response.title());
        assertTrue(response.completed());

    }

    @Test
    void shouldThrowWhenTaskDoesNotBelongToCurrentUser(){

        User user = new User(
                "robert@test.com",
                "password"
        );

        when(currentUserService.getCurrentUser())
                .thenReturn(user);

        when(taskRepository.findByIdAndUser(1L, user))
                .thenReturn(Optional.empty());

        assertThrows(
                TaskNotFoundException.class,
                () -> taskService.getTaskById(1L)
        );
    }
}
