package com.codesolutions.pmt.repository;

import com.codesolutions.pmt.model.Comment;
import com.codesolutions.pmt.model.Project;
import com.codesolutions.pmt.model.Task;
import com.codesolutions.pmt.model.User;

import java.util.List;

public interface CommentRepository extends BaseRepository<Comment, Long> {

    List<Comment> findByTask(Task task);

    List<Comment> findByTaskId(Long taskId);

    List<Comment> findByProject(Project project);

    List<Comment> findByProjectId(Long projectId);

    List<Comment> findByAuthor(User author);

    List<Comment> findByAuthorId(Long authorId);

    List<Comment> findByTaskIdOrderByCreatedAtDesc(Long taskId);

    List<Comment> findByProjectIdOrderByCreatedAtDesc(Long projectId);
}
