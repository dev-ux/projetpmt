package com.codesolutions.pmt.controller;

import com.codesolutions.pmt.dto.TaskDTO;
import com.codesolutions.pmt.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Task Management", description = "APIs for managing tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    @Operation(summary = "Get all tasks", description = "Retrieve all tasks in the system")
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        log.debug("GET /tasks - Getting all tasks");
        List<TaskDTO> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get task by ID", description = "Retrieve a specific task by its ID")
    public ResponseEntity<TaskDTO> getTaskById(
            @Parameter(description = "Task ID", required = true)
            @PathVariable Long id) {
        log.debug("GET /tasks/{} - Getting task by id", id);
        TaskDTO task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    @GetMapping("/project/{projectId}")
    @Operation(summary = "Get tasks by project", description = "Retrieve all tasks for a specific project")
    public ResponseEntity<List<TaskDTO>> getTasksByProject(
            @Parameter(description = "Project ID", required = true)
            @PathVariable Long projectId) {
        log.debug("GET /tasks/project/{} - Getting tasks by project", projectId);
        List<TaskDTO> tasks = taskService.getTasksByProject(projectId);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/assignee/{assigneeId}")
    @Operation(summary = "Get tasks by assignee", description = "Retrieve all tasks assigned to a specific user")
    public ResponseEntity<List<TaskDTO>> getTasksByAssignee(
            @Parameter(description = "Assignee ID", required = true)
            @PathVariable Long assigneeId) {
        log.debug("GET /tasks/assignee/{} - Getting tasks by assignee", assigneeId);
        List<TaskDTO> tasks = taskService.getTasksByAssignee(assigneeId);
        return ResponseEntity.ok(tasks);
    }

    @PostMapping
    @Operation(summary = "Create task", description = "Create a new task")
    public ResponseEntity<TaskDTO> createTask(
            @Parameter(description = "Task data", required = true)
            @Valid @RequestBody TaskDTO taskDTO) {
        log.debug("POST /tasks - Creating task: {}", taskDTO.getTitle());
        TaskDTO createdTask = taskService.createTask(taskDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update task", description = "Update an existing task")
    public ResponseEntity<TaskDTO> updateTask(
            @Parameter(description = "Task ID", required = true)
            @PathVariable Long id,
            @Parameter(description = "Updated task data", required = true)
            @Valid @RequestBody TaskDTO taskDTO) {
        log.debug("PUT /tasks/{} - Updating task", id);
        TaskDTO updatedTask = taskService.updateTask(id, taskDTO);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete task", description = "Delete a task")
    public ResponseEntity<Void> deleteTask(
            @Parameter(description = "Task ID", required = true)
            @PathVariable Long id) {
        log.debug("DELETE /tasks/{} - Deleting task", id);
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
