package com.codesolutions.pmt.service;

import com.codesolutions.pmt.dto.ProjectDTO;
import com.codesolutions.pmt.model.Project;
import com.codesolutions.pmt.repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    @Test
    void getAllProjects_ShouldReturnListOfProjects() {
        // Given
        Project project1 = new Project();
        project1.setId(1L);
        project1.setName("Project 1");
        
        Project project2 = new Project();
        project2.setId(2L);
        project2.setName("Project 2");
        
        List<Project> projects = Arrays.asList(project1, project2);
        
        when(projectRepository.findAll()).thenReturn(projects);
        
        // When
        List<ProjectDTO> result = projectService.getAllProjects();
        
        // Then
        assertEquals(2, result.size());
        assertEquals("Project 1", result.get(0).getName());
        assertEquals("Project 2", result.get(1).getName());
        verify(projectRepository, times(1)).findAll();
    }

    @Test
    void createProject_ShouldReturnCreatedProject() {
        // Given
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setName("New Project");
        
        Project savedProject = new Project();
        savedProject.setId(1L);
        savedProject.setName("New Project");
        
        when(projectRepository.save(any(Project.class))).thenReturn(savedProject);
        
        // When
        ProjectDTO result = projectService.createProject(projectDTO);
        
        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("New Project", result.getName());
        verify(projectRepository, times(1)).save(any(Project.class));
    }
}
