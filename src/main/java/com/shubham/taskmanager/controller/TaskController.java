package com.shubham.taskmanager.controller;

import com.shubham.taskmanager.dto.TaskDTO;
import com.shubham.taskmanager.model.Task;
import com.shubham.taskmanager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> createTask(@AuthenticationPrincipal UserDetails user,
                                           @RequestBody TaskDTO dto) {
        return ResponseEntity.ok(taskService.createTask(user.getUsername(), dto.getTitle(), dto.getDescription(), dto.getStatus(), dto.getDueDate()));
    }

    @GetMapping
    public ResponseEntity<List<Task>> getTasks(@AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.ok(taskService.getUserTasks(user.getUsername()));
    }
}
