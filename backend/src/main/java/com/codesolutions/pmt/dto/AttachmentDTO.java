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
public class AttachmentDTO {
    private Long id;
    private String filename;
    private String originalName;
    private String filePath;
    private Long fileSize;
    private String mimeType;
    private Long taskId;
    private String taskTitle;
    private Long projectId;
    private String projectName;
    private Long commentId;
    private Long uploadedById;
    private String uploadedByName;
    private LocalDateTime createdAt;
}
