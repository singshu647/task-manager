package com.shubham.taskmanager.service;

import com.shubham.taskmanager.model.Task;
import com.shubham.taskmanager.model.TaskStatus;
import com.shubham.taskmanager.model.Users;
import com.shubham.taskmanager.repository.TaskRepository;
import com.shubham.taskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public Task createTask(String username, String title, String desc, String status, String dueDate) {
        Users user = userRepository.findByUsername(username).orElseThrow();
        Task task = Task.builder()
                .title(title)
                .description(desc)
                .status(TaskStatus.valueOf(status))
                .dueDate(LocalDate.parse(dueDate))
                .user(user)
                .build();
        return taskRepository.save(task);
    }

    public List<Task> getUserTasks(String username) {
        Users user = userRepository.findByUsername(username).orElseThrow();
        return taskRepository.findByUser(user);
    }
}
