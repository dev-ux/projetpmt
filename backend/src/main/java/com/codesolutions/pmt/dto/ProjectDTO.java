package com.codesolutions.pmt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectDTO {
    private Long id;
    private String name;
    private String description;
    private Long statusId;
    private String statusName;
    private Long teamId;
    private String teamName;
    private Long createdById;
    private String createdByName;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal budget;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
