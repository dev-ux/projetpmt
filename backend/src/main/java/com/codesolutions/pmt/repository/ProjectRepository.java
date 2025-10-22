package com.codesolutions.pmt.repository;

import com.codesolutions.pmt.model.Project;
import com.codesolutions.pmt.model.User;

import java.util.List;

public interface ProjectRepository extends BaseRepository<Project, Long> {

    List<Project> findByCreatedBy(User createdBy);

    List<Project> findByTeamId(Long teamId);

    List<Project> findByStatusId(Long statusId);

    List<Project> findByMembersContaining(User user);
}
