package com.codesolutions.pmt.repository;

import com.codesolutions.pmt.model.TaskStatus;

public interface TaskStatusRepository extends BaseRepository<TaskStatus, Long> {

    boolean existsByName(String name);
}
