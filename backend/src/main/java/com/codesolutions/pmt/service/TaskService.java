package com.codesolutions.pmt.service;

import com.codesolutions.pmt.dto.TaskDTO;

import java.util.List;

public interface TaskService {

    List<TaskDTO> getAllTasks();

    TaskDTO getTaskById(Long id);

    List<TaskDTO> getTasksByProject(Long projectId);

    List<TaskDTO> getTasksByAssignee(Long assigneeId);

    TaskDTO createTask(TaskDTO taskDTO);

    TaskDTO updateTask(Long id, TaskDTO taskDTO);

    void deleteTask(Long id);
}
