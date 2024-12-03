package com.example.task_manager.controllers;

import com.example.task_manager.dto.CreateTaskDTO;
import com.example.task_manager.entities.TaskEntity;
import com.example.task_manager.repositories.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping()
    public ResponseEntity<List<TaskEntity>> getTasks() {
        var tasks=taskService.getTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskEntity> getTaskById(@PathVariable("id") Integer id) {
        var task=taskService.getTaskById(id);
        if (task==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @PostMapping("")
    public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDTO body) {
        var task=taskService.addTask(body.getTitle(),body.getDescription(), body.getDeadline());
        return ResponseEntity.ok(task);
    }
}
