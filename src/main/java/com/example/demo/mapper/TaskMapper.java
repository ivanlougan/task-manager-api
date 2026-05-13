package com.example.demo.mapper;

import com.example.demo.dto.CreateTaskRequest;
import com.example.demo.dto.TaskResponse;
import com.example.demo.model.Task;

public class TaskMapper {

    public static Task toEntity(CreateTaskRequest request) {
        return new Task(
                request.getTitle(),
                false
        );
    }

    public static TaskResponse toResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.isCompleted()
        );
    }
}
