package com.example.demo.service;

import com.example.demo.dto.CreateTaskRequest;
import com.example.demo.dto.TaskResponse;
import com.example.demo.mapper.TaskMapper;
import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements TaskServiceInterface{

    private final TaskRepository repo;

    public TaskService(TaskRepository repo) {
        this.repo = repo;
    }

    @Override
    public TaskResponse createTask(CreateTaskRequest request) {

        Task task = TaskMapper.toEntity(request);

        Task savedTask = repo.save(task);

        return TaskMapper.toResponse(savedTask);
    }

    @Override
    public List<TaskResponse> getAllTasks() {

        return repo.findAll()
                .stream()
                .map(TaskMapper::toResponse)
                .toList();
    }

}
