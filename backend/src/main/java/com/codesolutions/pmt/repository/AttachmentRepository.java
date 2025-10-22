package com.codesolutions.pmt.repository;

import com.codesolutions.pmt.model.Attachment;
import com.codesolutions.pmt.model.Project;
import com.codesolutions.pmt.model.Task;
import com.codesolutions.pmt.model.User;

import java.util.List;

public interface AttachmentRepository extends BaseRepository<Attachment, Long> {

    List<Attachment> findByTask(Task task);

    List<Attachment> findByTaskId(Long taskId);

    List<Attachment> findByProject(Project project);

    List<Attachment> findByProjectId(Long projectId);

    List<Attachment> findByUploadedBy(User uploadedBy);

    List<Attachment> findByUploadedById(Long uploadedById);

    List<Attachment> findByFilenameContainingIgnoreCase(String filename);
}
