package com.example.demo;

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
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void shouldUpdateTask() {

        Task existingTask = new Task();

        existingTask.setId(1L);
        existingTask.setTitle("Old title");
        existingTask.setCompleted(false);

        UpdateTaskRequest request = new UpdateTaskRequest();

        ReflectionTestUtils.setField(request, "title", "New title");
        ReflectionTestUtils.setField(request, "completed", true);

        when(taskRepository.findById(1L))
                .thenReturn(Optional.of(existingTask));

        when(taskRepository.save(any(Task.class)))
                .thenReturn(existingTask);

        TaskResponse response = taskService.updateTask(1L, request);

        assertEquals("New title", response.getTitle());

        assertTrue(response.isCompleted());

    }
}
