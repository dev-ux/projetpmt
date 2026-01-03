package com.codesolutions.pmt.controller;

import com.codesolutions.pmt.dto.ProjectDTO;
import com.codesolutions.pmt.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectControllerTest {

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private ProjectController projectController;

    @Test
    void getAllProjects_ShouldReturnListOfProjects() {
        // Given
        ProjectDTO project1 = new ProjectDTO();
        project1.setId(1L);
        project1.setName("Project 1");
        
        ProjectDTO project2 = new ProjectDTO();
        project2.setId(2L);
        project2.setName("Project 2");
        
        List<ProjectDTO> projects = Arrays.asList(project1, project2);
        
        when(projectService.getAllProjects()).thenReturn(projects);
        
        // When
        ResponseEntity<List<ProjectDTO>> response = projectController.getAllProjects();
        
        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(projectService, times(1)).getAllProjects();
    }

    @Test
    void getProjectById_ShouldReturnProject() {
        // Given
        ProjectDTO project = new ProjectDTO();
        project.setId(1L);
        project.setName("Test Project");
        
        when(projectService.getProjectById(1L)).thenReturn(project);
        
        // When
        ResponseEntity<ProjectDTO> response = projectController.getProjectById(1L);
        
        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        assertEquals("Test Project", response.getBody().getName());
        verify(projectService, times(1)).getProjectById(1L);
    }

    @Test
    void createProject_ShouldReturnCreatedProject() {
        // Given
        ProjectDTO newProject = new ProjectDTO();
        newProject.setName("New Project");
        
        ProjectDTO createdProject = new ProjectDTO();
        createdProject.setId(1L);
        createdProject.setName("New Project");
        
        when(projectService.createProject(any(ProjectDTO.class))).thenReturn(createdProject);
        
        // When
        ResponseEntity<ProjectDTO> response = projectController.createProject(newProject);
        
        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        assertEquals("New Project", response.getBody().getName());
        verify(projectService, times(1)).createProject(any(ProjectDTO.class));
    }

    @Test
    void updateProject_ShouldReturnUpdatedProject() {
        // Given
        ProjectDTO updatedProject = new ProjectDTO();
        updatedProject.setId(1L);
        updatedProject.setName("Updated Project");
        
        when(projectService.updateProject(anyLong(), any(ProjectDTO.class))).thenReturn(updatedProject);
        
        // When
        ResponseEntity<ProjectDTO> response = projectController.updateProject(1L, updatedProject);
        
        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
        assertEquals("Updated Project", response.getBody().getName());
        verify(projectService, times(1)).updateProject(anyLong(), any(ProjectDTO.class));
    }

    @Test
    void deleteProject_ShouldReturnNoContent() {
        // Given
        doNothing().when(projectService).deleteProject(1L);
        
        // When
        ResponseEntity<Void> response = projectController.deleteProject(1L);
        
        // Then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(projectService, times(1)).deleteProject(1L);
    }
}
