package com.codesolutions.pmt.controller;

import com.codesolutions.pmt.dto.TaskDTO;
import com.codesolutions.pmt.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllTasks_ShouldReturnTaskList() throws Exception {
        TaskDTO task1 = new TaskDTO();
        task1.setId(1L);
        task1.setTitle("Task 1");
        task1.setDescription("Description 1");

        TaskDTO task2 = new TaskDTO();
        task2.setId(2L);
        task2.setTitle("Task 2");
        task2.setDescription("Description 2");

        List<TaskDTO> tasks = Arrays.asList(task1, task2);
        when(taskService.getAllTasks()).thenReturn(tasks);

        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Task 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].title").value("Task 2"));
    }

    @Test
    void getTaskById_ShouldReturnTask() throws Exception {
        TaskDTO task = new TaskDTO();
        task.setId(1L);
        task.setTitle("Test Task");
        task.setDescription("Test Description");

        when(taskService.getTaskById(1L)).thenReturn(task);

        mockMvc.perform(get("/api/tasks/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Test Task"));
    }

    @Test
    void createTask_ShouldReturnCreatedTask() throws Exception {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle("New Task");
        taskDTO.setDescription("New task description");

        TaskDTO createdTask = new TaskDTO();
        createdTask.setId(1L);
        createdTask.setTitle("New Task");
        createdTask.setDescription("New task description");

        when(taskService.createTask(any(TaskDTO.class))).thenReturn(createdTask);

        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taskDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("New Task"));
    }

    @Test
    void updateTask_ShouldReturnUpdatedTask() throws Exception {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle("Updated Task");
        taskDTO.setDescription("Updated description");

        TaskDTO updatedTask = new TaskDTO();
        updatedTask.setId(1L);
        updatedTask.setTitle("Updated Task");
        updatedTask.setDescription("Updated description");

        when(taskService.updateTask(any(Long.class), any(TaskDTO.class))).thenReturn(updatedTask);

        mockMvc.perform(put("/api/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taskDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Updated Task"));
    }

    @Test
    void deleteTask_ShouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/tasks/1"))
                .andExpect(status().isNoContent());
    }
}
