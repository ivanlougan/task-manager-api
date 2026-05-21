package com.example.demo.service;

import com.example.demo.dto.CreateTaskRequest;
import com.example.demo.dto.TaskResponse;
import com.example.demo.dto.UpdateTaskRequest;
import com.example.demo.exception.TaskNotFoundException;
import com.example.demo.mapper.TaskMapper;
import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements TaskServiceInterface{

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository repo) {
        this.taskRepository = repo;
    }

    @Override
    public TaskResponse createTask(CreateTaskRequest request) {

        Task task = TaskMapper.toEntity(request);

        Task savedTask = taskRepository.save(task);

        return TaskMapper.toResponse(savedTask);
    }

    @Override
    public List<TaskResponse> getAllTasks() {

        return taskRepository.findAll()
                .stream()
                .map(TaskMapper::toResponse)
                .toList();
    }

    @Override
    public TaskResponse getTaskById(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new TaskNotFoundException(
                                "Task not found with id: " + id
                        ));

        return TaskMapper.toResponse(task);
    }

    @Override
    public void deleteTaskById(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new TaskNotFoundException(
                                "Task not found with id: " + id
                        ));

        taskRepository.delete(task);
    }

    @Override
    public TaskResponse updateTask(Long id, UpdateTaskRequest request) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new TaskNotFoundException(
                                "Task not found with id: " + id
                        ));

        task.setTitle(request.getTitle());
        task.setCompleted(request.isCompleted());

        Task updatedTask = taskRepository.save(task);

        return TaskMapper.toResponse(updatedTask);
    }

    @Override
    public TaskResponse completeTask(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new TaskNotFoundException(
                                "Task not found with id: " + id
                ));

        task.setCompleted(true);

        Task updatedTask = taskRepository.save(task);

        return TaskMapper.toResponse(updatedTask);


    }
}
