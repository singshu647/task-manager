package com.shubham.taskmanager.repository;

import com.shubham.taskmanager.model.Task;
import com.shubham.taskmanager.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(Users user);
}

