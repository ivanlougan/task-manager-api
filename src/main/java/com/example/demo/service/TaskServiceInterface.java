package com.example.demo.service;

import com.example.demo.dto.CreateTaskRequest;
import com.example.demo.dto.TaskResponse;
import com.example.demo.dto.UpdateTaskRequest;
import com.example.demo.model.User;

import java.util.List;

public interface TaskServiceInterface {

    TaskResponse createTask(CreateTaskRequest request);

    List<TaskResponse> getAllTasks();

    TaskResponse getTaskById(Long id);

    void deleteTaskById(Long id);

    TaskResponse completeTask(Long id);

    TaskResponse updateTask(Long id, UpdateTaskRequest request);


}
