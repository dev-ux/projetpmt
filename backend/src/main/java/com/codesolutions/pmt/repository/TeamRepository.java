package com.codesolutions.pmt.repository;

import com.codesolutions.pmt.model.Team;
import com.codesolutions.pmt.model.User;

import java.util.List;

public interface TeamRepository extends BaseRepository<Team, Long> {

    List<Team> findByCreatedBy(User createdBy);

    List<Team> findByMembersContaining(User user);

    List<Team> findByNameContainingIgnoreCase(String name);
}
