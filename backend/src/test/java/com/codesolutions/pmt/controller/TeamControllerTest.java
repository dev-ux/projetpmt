package com.codesolutions.pmt.controller;

import com.codesolutions.pmt.dto.TeamDTO;
import com.codesolutions.pmt.service.TeamService;
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

@WebMvcTest(TeamController.class)
class TeamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeamService teamService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllTeams_ShouldReturnTeamList() throws Exception {
        TeamDTO team1 = new TeamDTO();
        team1.setId(1L);
        team1.setName("Development Team");
        team1.setDescription("Main development team");

        TeamDTO team2 = new TeamDTO();
        team2.setId(2L);
        team2.setName("Design Team");
        team2.setDescription("UI/UX design team");

        List<TeamDTO> teams = Arrays.asList(team1, team2);
        when(teamService.getAllTeams()).thenReturn(teams);

        mockMvc.perform(get("/api/teams"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Development Team"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Design Team"));
    }

    @Test
    void getTeamById_ShouldReturnTeam() throws Exception {
        TeamDTO team = new TeamDTO();
        team.setId(1L);
        team.setName("Development Team");
        team.setDescription("Main development team");

        when(teamService.getTeamById(1L)).thenReturn(team);

        mockMvc.perform(get("/api/teams/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Development Team"));
    }

    @Test
    void createTeam_ShouldReturnCreatedTeam() throws Exception {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setName("New Team");
        teamDTO.setDescription("New team description");

        TeamDTO createdTeam = new TeamDTO();
        createdTeam.setId(1L);
        createdTeam.setName("New Team");
        createdTeam.setDescription("New team description");

        when(teamService.createTeam(any(TeamDTO.class))).thenReturn(createdTeam);

        mockMvc.perform(post("/api/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(teamDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("New Team"));
    }

    @Test
    void updateTeam_ShouldReturnUpdatedTeam() throws Exception {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setName("Updated Team");
        teamDTO.setDescription("Updated description");

        TeamDTO updatedTeam = new TeamDTO();
        updatedTeam.setId(1L);
        updatedTeam.setName("Updated Team");
        updatedTeam.setDescription("Updated description");

        when(teamService.updateTeam(any(Long.class), any(TeamDTO.class))).thenReturn(updatedTeam);

        mockMvc.perform(put("/api/teams/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(teamDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated Team"));
    }

    @Test
    void deleteTeam_ShouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/teams/1"))
                .andExpect(status().isNoContent());
    }
}
