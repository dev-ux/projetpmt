package com.codesolutions.pmt.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder.Default;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Email
    @Size(max = 255)
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank
    @Size(max = 255)
    @Column(nullable = false)
    private String password;

    @NotBlank
    @Size(max = 100)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank
    @Size(max = 100)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Size(max = 500)
    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "is_active")
    @Default
    private Boolean isActive = true;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Relations
    @ManyToMany(mappedBy = "members")
    @Default
    private List<Team> teams = new ArrayList<>();

    @ManyToMany(mappedBy = "members")
    @Default
    private List<Project> projects = new ArrayList<>();

    @OneToMany(mappedBy = "createdBy")
    @Default
    private List<Team> createdTeams = new ArrayList<>();

    @OneToMany(mappedBy = "createdBy")
    @Default
    private List<Project> createdProjects = new ArrayList<>();

    @OneToMany(mappedBy = "assignee")
    @Default
    private List<Task> assignedTasks = new ArrayList<>();

    @OneToMany(mappedBy = "createdBy")
    @Default
    private List<Task> createdTasks = new ArrayList<>();

    @OneToMany(mappedBy = "author")
    @Default
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "uploadedBy")
    @Default
    private List<Attachment> attachments = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Méthodes utilitaires
    public String getFullName() {
        return firstName + " " + lastName;
    }
}
