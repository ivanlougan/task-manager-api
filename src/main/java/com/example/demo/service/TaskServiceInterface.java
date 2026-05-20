package com.example.demo.service;

import com.example.demo.dto.CreateTaskRequest;
import com.example.demo.dto.TaskResponse;

import java.util.List;

public interface TaskServiceInterface {

    TaskResponse createTask(CreateTaskRequest request);

    List<TaskResponse> getAllTasks();

    TaskResponse getTaskById(Long id);

    void deleteTaskById(Long id);
}
