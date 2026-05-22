package com.example.demo;

import com.example.demo.dto.CreateTaskRequest;
import com.example.demo.dto.TaskResponse;
import com.example.demo.dto.UpdateTaskRequest;
import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
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

    @InjectMocks
    private TaskService taskService;


    @Test
    void shouldCreateTask() {
        CreateTaskRequest request = new CreateTaskRequest("Learn Python cause AI..");

        Task task = new Task(
                "Learn Python cause AI..",
                false
        );

        task.setId(1L);

        when(taskRepository.save(any(Task.class)))
                .thenReturn(task);

        TaskResponse response = taskService.createTask(request);

        assertEquals(1L, response.id());
        assertEquals("Learn Python cause AI..", response.title());
        assertFalse(response.completed());

        verify(taskRepository).save(any(Task.class));
    }

    @Test
    void shouldUpdateTask() {

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

        when(taskRepository.findById(1L))
                .thenReturn(Optional.of(task));

        when(taskRepository.save(any(Task.class)))
                .thenReturn(task);

        TaskResponse response = taskService.updateTask(1L, request);

        assertEquals("New title", response.title());
        assertTrue(response.completed());

    }
}
