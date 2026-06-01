package com.example.demo.controller;

import com.example.demo.dto.CreateTaskRequest;
import com.example.demo.dto.TaskResponse;
import com.example.demo.dto.UpdateTaskRequest;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
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
    private final UserRepository userRepository;

    public TaskController(TaskService service, UserRepository userRepository) {
        this.taskService = service;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks() {

        User user = userRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Default user not found"));

        List<TaskResponse> tasks = taskService.getTasksByUser(user);

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById( @PathVariable Long id ) {

        return ResponseEntity.ok(
                taskService.getTaskById(id)
        );
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask( @Valid @RequestBody CreateTaskRequest request) {

        User user = userRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Default user not found"));

        TaskResponse response = taskService.createTask(request, user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskById( @PathVariable Long id) {

        taskService.deleteTaskById(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable Long id,
            @RequestBody UpdateTaskRequest request) {

        return ResponseEntity.ok(taskService.updateTask(id, request));
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<TaskResponse> completeTask(@PathVariable Long id) {

        return ResponseEntity.ok(taskService.completeTask(id));
    }
}
