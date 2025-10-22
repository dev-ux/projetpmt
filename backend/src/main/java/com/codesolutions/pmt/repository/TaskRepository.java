package com.codesolutions.pmt.repository;

import com.codesolutions.pmt.model.Project;
import com.codesolutions.pmt.model.Task;
import com.codesolutions.pmt.model.TaskPriority;
import com.codesolutions.pmt.model.User;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends BaseRepository<Task, Long> {

    List<Task> findByProject(Project project);

    List<Task> findByProjectId(Long projectId);

    List<Task> findByAssignee(User assignee);

    List<Task> findByAssigneeId(Long assigneeId);

    List<Task> findByCreatedBy(User createdBy);

    List<Task> findByStatusId(Long statusId);

    List<Task> findByPriority(TaskPriority priority);

    List<Task> findByProjectIdAndAssigneeId(Long projectId, Long assigneeId);

    List<Task> findByProjectIdAndStatusId(Long projectId, Long statusId);

    List<Task> findByDueDate(LocalDate dueDate);

    List<Task> findByDueDateBetween(LocalDate startDate, LocalDate endDate);

    List<Task> findByProjectIdOrderByDueDateAsc(Long projectId);

    List<Task> findByProjectIdOrderByPriorityDesc(Long projectId);
}
