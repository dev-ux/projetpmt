package com.codesolutions.pmt.service;

import com.codesolutions.pmt.dto.ProjectDTO;
import com.codesolutions.pmt.dto.UserDTO;

import java.util.List;

public interface ProjectService {

    List<ProjectDTO> getAllProjects();

    ProjectDTO getProjectById(Long id);

    List<ProjectDTO> getProjectsByUser(Long userId);

    List<ProjectDTO> getProjectsByTeam(Long teamId);

    ProjectDTO createProject(ProjectDTO projectDTO);

    ProjectDTO updateProject(Long id, ProjectDTO projectDTO);

    void deleteProject(Long id);

    void addMemberToProject(Long projectId, Long userId);

    void removeMemberFromProject(Long projectId, Long userId);

    List<UserDTO> getProjectMembers(Long projectId);
}
