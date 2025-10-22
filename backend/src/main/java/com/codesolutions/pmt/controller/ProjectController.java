package com.codesolutions.pmt.controller;

import com.codesolutions.pmt.dto.ProjectDTO;
import com.codesolutions.pmt.service.ProjectService;
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
@RequestMapping("/projects")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Project Management", description = "APIs for managing projects")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    @Operation(summary = "Get all projects", description = "Retrieve all projects in the system")
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        log.debug("GET /projects - Getting all projects");
        List<ProjectDTO> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get project by ID", description = "Retrieve a specific project by its ID")
    public ResponseEntity<ProjectDTO> getProjectById(
            @Parameter(description = "Project ID", required = true)
            @PathVariable Long id) {
        log.debug("GET /projects/{} - Getting project by id", id);
        ProjectDTO project = projectService.getProjectById(id);
        return ResponseEntity.ok(project);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get projects by user", description = "Retrieve all projects for a specific user")
    public ResponseEntity<List<ProjectDTO>> getProjectsByUser(
            @Parameter(description = "User ID", required = true)
            @PathVariable Long userId) {
        log.debug("GET /projects/user/{} - Getting projects by user", userId);
        List<ProjectDTO> projects = projectService.getProjectsByUser(userId);
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/team/{teamId}")
    @Operation(summary = "Get projects by team", description = "Retrieve all projects for a specific team")
    public ResponseEntity<List<ProjectDTO>> getProjectsByTeam(
            @Parameter(description = "Team ID", required = true)
            @PathVariable Long teamId) {
        log.debug("GET /projects/team/{} - Getting projects by team", teamId);
        List<ProjectDTO> projects = projectService.getProjectsByTeam(teamId);
        return ResponseEntity.ok(projects);
    }

    @PostMapping
    @Operation(summary = "Create project", description = "Create a new project")
    public ResponseEntity<ProjectDTO> createProject(
            @Parameter(description = "Project data", required = true)
            @Valid @RequestBody ProjectDTO projectDTO) {
        log.debug("POST /projects - Creating project: {}", projectDTO.getName());
        ProjectDTO createdProject = projectService.createProject(projectDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update project", description = "Update an existing project")
    public ResponseEntity<ProjectDTO> updateProject(
            @Parameter(description = "Project ID", required = true)
            @PathVariable Long id,
            @Parameter(description = "Updated project data", required = true)
            @Valid @RequestBody ProjectDTO projectDTO) {
        log.debug("PUT /projects/{} - Updating project", id);
        ProjectDTO updatedProject = projectService.updateProject(id, projectDTO);
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete project", description = "Delete a project")
    public ResponseEntity<Void> deleteProject(
            @Parameter(description = "Project ID", required = true)
            @PathVariable Long id) {
        log.debug("DELETE /projects/{} - Deleting project", id);
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/members/{userId}")
    @Operation(summary = "Add member to project", description = "Add a user as a member to a project")
    public ResponseEntity<Void> addMemberToProject(
            @Parameter(description = "Project ID", required = true)
            @PathVariable Long id,
            @Parameter(description = "User ID", required = true)
            @PathVariable Long userId) {
        log.debug("POST /projects/{}/members/{} - Adding member to project", id, userId);
        projectService.addMemberToProject(id, userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/members/{userId}")
    @Operation(summary = "Remove member from project", description = "Remove a user from a project")
    public ResponseEntity<Void> removeMemberFromProject(
            @Parameter(description = "Project ID", required = true)
            @PathVariable Long id,
            @Parameter(description = "User ID", required = true)
            @PathVariable Long userId) {
        log.debug("DELETE /projects/{}/members/{} - Removing member from project", id, userId);
        projectService.removeMemberFromProject(id, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/members")
    @Operation(summary = "Get project members", description = "Retrieve all members of a project")
    public ResponseEntity<List<com.codesolutions.pmt.dto.UserDTO>> getProjectMembers(
            @Parameter(description = "Project ID", required = true)
            @PathVariable Long id) {
        log.debug("GET /projects/{}/members - Getting project members", id);
        List<com.codesolutions.pmt.dto.UserDTO> members = projectService.getProjectMembers(id);
        return ResponseEntity.ok(members);
    }
}
