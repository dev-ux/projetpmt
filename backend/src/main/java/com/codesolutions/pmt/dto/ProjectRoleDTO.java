package com.codesolutions.pmt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectRoleDTO {
    private Long id;
    private String name;
    private String description;
    private String permissions;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
