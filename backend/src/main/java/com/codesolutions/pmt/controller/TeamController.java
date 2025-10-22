package com.codesolutions.pmt.controller;

import com.codesolutions.pmt.dto.TeamDTO;
import com.codesolutions.pmt.service.TeamService;
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
@RequestMapping("/teams")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Team Management", description = "APIs for managing teams")
public class TeamController {

    private final TeamService teamService;

    @GetMapping
    @Operation(summary = "Get all teams", description = "Retrieve all teams in the system")
    public ResponseEntity<List<TeamDTO>> getAllTeams() {
        log.debug("GET /teams - Getting all teams");
        List<TeamDTO> teams = teamService.getAllTeams();
        return ResponseEntity.ok(teams);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get team by ID", description = "Retrieve a specific team by its ID")
    public ResponseEntity<TeamDTO> getTeamById(
            @Parameter(description = "Team ID", required = true)
            @PathVariable Long id) {
        log.debug("GET /teams/{} - Getting team by id", id);
        TeamDTO team = teamService.getTeamById(id);
        return ResponseEntity.ok(team);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get teams by user", description = "Retrieve all teams for a specific user")
    public ResponseEntity<List<TeamDTO>> getTeamsByUser(
            @Parameter(description = "User ID", required = true)
            @PathVariable Long userId) {
        log.debug("GET /teams/user/{} - Getting teams by user", userId);
        List<TeamDTO> teams = teamService.getTeamsByUser(userId);
        return ResponseEntity.ok(teams);
    }

    @PostMapping
    @Operation(summary = "Create team", description = "Create a new team")
    public ResponseEntity<TeamDTO> createTeam(
            @Parameter(description = "Team data", required = true)
            @Valid @RequestBody TeamDTO teamDTO) {
        log.debug("POST /teams - Creating team: {}", teamDTO.getName());
        TeamDTO createdTeam = teamService.createTeam(teamDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTeam);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update team", description = "Update an existing team")
    public ResponseEntity<TeamDTO> updateTeam(
            @Parameter(description = "Team ID", required = true)
            @PathVariable Long id,
            @Parameter(description = "Updated team data", required = true)
            @Valid @RequestBody TeamDTO teamDTO) {
        log.debug("PUT /teams/{} - Updating team", id);
        TeamDTO updatedTeam = teamService.updateTeam(id, teamDTO);
        return ResponseEntity.ok(updatedTeam);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete team", description = "Delete a team")
    public ResponseEntity<Void> deleteTeam(
            @Parameter(description = "Team ID", required = true)
            @PathVariable Long id) {
        log.debug("DELETE /teams/{} - Deleting team", id);
        teamService.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/members/{userId}")
    @Operation(summary = "Add member to team", description = "Add a user as a member to a team")
    public ResponseEntity<Void> addMemberToTeam(
            @Parameter(description = "Team ID", required = true)
            @PathVariable Long id,
            @Parameter(description = "User ID", required = true)
            @PathVariable Long userId) {
        log.debug("POST /teams/{}/members/{} - Adding member to team", id, userId);
        teamService.addMemberToTeam(id, userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/members/{userId}")
    @Operation(summary = "Remove member from team", description = "Remove a user from a team")
    public ResponseEntity<Void> removeMemberFromTeam(
            @Parameter(description = "Team ID", required = true)
            @PathVariable Long id,
            @Parameter(description = "User ID", required = true)
            @PathVariable Long userId) {
        log.debug("DELETE /teams/{}/members/{} - Removing member from team", id, userId);
        teamService.removeMemberFromTeam(id, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/members")
    @Operation(summary = "Get team members", description = "Retrieve all members of a team")
    public ResponseEntity<List<com.codesolutions.pmt.dto.UserDTO>> getTeamMembers(
            @Parameter(description = "Team ID", required = true)
            @PathVariable Long id) {
        log.debug("GET /teams/{}/members - Getting team members", id);
        List<com.codesolutions.pmt.dto.UserDTO> members = teamService.getTeamMembers(id);
        return ResponseEntity.ok(members);
    }
}
