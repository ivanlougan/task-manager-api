package com.example.demo.controller;

import com.example.demo.dto.CreateTaskRequest;
import com.example.demo.dto.TaskResponse;
import com.example.demo.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService service) {
        this.taskService = service;
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        return ResponseEntity.ok(
                taskService.getAllTasks()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById( @PathVariable Long id ) {

        return ResponseEntity.ok(
                taskService.getTaskById(id)
        );
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask( @Valid @RequestBody CreateTaskRequest request) {
        TaskResponse response = taskService.createTask(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskById( @PathVariable Long id) {

        taskService.deleteTaskById(id);

        return ResponseEntity.noContent().build();
    }
}
