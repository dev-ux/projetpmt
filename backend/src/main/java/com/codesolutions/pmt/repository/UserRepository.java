package com.codesolutions.pmt.repository;

import com.codesolutions.pmt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.isActive = true")
    List<User> findAllActiveUsers();

    @Query("SELECT u FROM User u WHERE u.isActive = true AND " +
           "(LOWER(u.firstName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(u.lastName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(u.email) LIKE LOWER(CONCAT('%', :search, '%')))")
    List<User> searchActiveUsers(@Param("search") String search);

    @Query("SELECT u FROM User u JOIN u.teams t WHERE t.id = :teamId AND u.isActive = true")
    List<User> findActiveUsersByTeamId(@Param("teamId") Long teamId);

    @Query("SELECT u FROM User u JOIN u.projects p WHERE p.id = :projectId AND u.isActive = true")
    List<User> findActiveUsersByProjectId(@Param("projectId") Long projectId);
}
