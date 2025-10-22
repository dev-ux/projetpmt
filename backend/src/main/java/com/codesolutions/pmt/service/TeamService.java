package com.codesolutions.pmt.service;

import com.codesolutions.pmt.dto.TeamDTO;
import com.codesolutions.pmt.dto.UserDTO;

import java.util.List;

public interface TeamService {

    List<TeamDTO> getAllTeams();

    TeamDTO getTeamById(Long id);

    List<TeamDTO> getTeamsByUser(Long userId);

    TeamDTO createTeam(TeamDTO teamDTO);

    TeamDTO updateTeam(Long id, TeamDTO teamDTO);

    void deleteTeam(Long id);

    void addMemberToTeam(Long teamId, Long userId);

    void removeMemberFromTeam(Long teamId, Long userId);

    List<UserDTO> getTeamMembers(Long teamId);
}
