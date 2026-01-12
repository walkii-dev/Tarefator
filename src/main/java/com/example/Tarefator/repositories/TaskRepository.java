package com.example.Tarefator.repositories;

import com.example.Tarefator.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {
}
