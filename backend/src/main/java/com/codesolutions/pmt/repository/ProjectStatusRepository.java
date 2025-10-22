package com.codesolutions.pmt.repository;

import com.codesolutions.pmt.model.ProjectStatus;

public interface ProjectStatusRepository extends BaseRepository<ProjectStatus, Long> {

    boolean existsByName(String name);
}
