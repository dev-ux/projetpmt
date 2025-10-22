package com.codesolutions.pmt.service;

import com.codesolutions.pmt.dto.CommentDTO;

import java.util.List;

public interface CommentService {

    List<CommentDTO> getAllComments();

    CommentDTO getCommentById(Long id);

    List<CommentDTO> getCommentsByTask(Long taskId);

    List<CommentDTO> getCommentsByProject(Long projectId);

    CommentDTO createComment(CommentDTO commentDTO);

    CommentDTO updateComment(Long id, CommentDTO commentDTO);

    void deleteComment(Long id);
}
