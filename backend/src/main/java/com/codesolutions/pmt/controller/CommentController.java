package com.codesolutions.pmt.controller;

import com.codesolutions.pmt.dto.CommentDTO;
import com.codesolutions.pmt.service.CommentService;
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
@RequestMapping("/comments")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Comment Management", description = "APIs for managing comments")
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    @Operation(summary = "Get all comments", description = "Retrieve all comments in the system")
    public ResponseEntity<List<CommentDTO>> getAllComments() {
        log.debug("GET /comments - Getting all comments");
        List<CommentDTO> comments = commentService.getAllComments();
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get comment by ID", description = "Retrieve a specific comment by its ID")
    public ResponseEntity<CommentDTO> getCommentById(
            @Parameter(description = "Comment ID", required = true)
            @PathVariable Long id) {
        log.debug("GET /comments/{} - Getting comment by id", id);
        CommentDTO comment = commentService.getCommentById(id);
        return ResponseEntity.ok(comment);
    }

    @GetMapping("/task/{taskId}")
    @Operation(summary = "Get comments by task", description = "Retrieve all comments for a specific task")
    public ResponseEntity<List<CommentDTO>> getCommentsByTask(
            @Parameter(description = "Task ID", required = true)
            @PathVariable Long taskId) {
        log.debug("GET /comments/task/{} - Getting comments by task", taskId);
        List<CommentDTO> comments = commentService.getCommentsByTask(taskId);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/project/{projectId}")
    @Operation(summary = "Get comments by project", description = "Retrieve all comments for a specific project")
    public ResponseEntity<List<CommentDTO>> getCommentsByProject(
            @Parameter(description = "Project ID", required = true)
            @PathVariable Long projectId) {
        log.debug("GET /comments/project/{} - Getting comments by project", projectId);
        List<CommentDTO> comments = commentService.getCommentsByProject(projectId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping
    @Operation(summary = "Create comment", description = "Create a new comment")
    public ResponseEntity<CommentDTO> createComment(
            @Parameter(description = "Comment data", required = true)
            @Valid @RequestBody CommentDTO commentDTO) {
        log.debug("POST /comments - Creating comment");
        CommentDTO createdComment = commentService.createComment(commentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update comment", description = "Update an existing comment")
    public ResponseEntity<CommentDTO> updateComment(
            @Parameter(description = "Comment ID", required = true)
            @PathVariable Long id,
            @Parameter(description = "Updated comment data", required = true)
            @Valid @RequestBody CommentDTO commentDTO) {
        log.debug("PUT /comments/{} - Updating comment", id);
        CommentDTO updatedComment = commentService.updateComment(id, commentDTO);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete comment", description = "Delete a comment")
    public ResponseEntity<Void> deleteComment(
            @Parameter(description = "Comment ID", required = true)
            @PathVariable Long id) {
        log.debug("DELETE /comments/{} - Deleting comment", id);
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
