package com.example.demo.service;

import com.example.demo.dto.CreateTaskRequest;
import com.example.demo.dto.TaskResponse;
import com.example.demo.dto.UpdateTaskRequest;
import com.example.demo.exception.TaskNotFoundException;
import com.example.demo.mapper.TaskMapper;
import com.example.demo.model.Task;
import com.example.demo.model.User;
import com.example.demo.repository.TaskRepository;
import com.example.demo.security.CurrentUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements TaskServiceInterface{

    private final TaskRepository taskRepository;
    private final CurrentUserService currentUserService;

    public TaskService(TaskRepository repo, CurrentUserService currentUserService) {
        this.taskRepository = repo;
        this.currentUserService = currentUserService;
    }

    @Override
    public TaskResponse createTask(CreateTaskRequest request) {

        User user = currentUserService.getCurrentUser();

        Task task = new Task(
                request.title(),
                false
        );

        task.setUser(user);

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

        User currentUser = currentUserService.getCurrentUser();

        Task task = taskRepository.findByIdAndUser(id, currentUser)
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

        task.setTitle(request.title());
        task.setCompleted(request.completed());

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

    public List<TaskResponse> getTasksByUser(User user) {
        List<Task> tasks = taskRepository.findByUser(user);

        return tasks.stream()
                .map(TaskMapper::toResponse)
                .toList();
    }
}
