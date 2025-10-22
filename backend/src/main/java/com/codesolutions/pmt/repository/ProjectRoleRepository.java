package com.codesolutions.pmt.repository;

import com.codesolutions.pmt.model.ProjectRole;

public interface ProjectRoleRepository extends BaseRepository<ProjectRole, Long> {

    boolean existsByName(String name);
}
