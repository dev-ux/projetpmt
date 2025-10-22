-- PMT (Project Management Tool) Database Schema
-- Création de la base de données pour la plateforme de gestion de projet

-- Table des statuts de projets
CREATE TABLE project_status (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table des statuts de tâches
CREATE TABLE task_status (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE,
    description TEXT,
    color VARCHAR(7) DEFAULT '#007bff',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table des rôles d'utilisateurs dans les projets
CREATE TABLE project_role (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE,
    description TEXT,
    permissions TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table des utilisateurs
CREATE TABLE users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    avatar_url VARCHAR(500),
    is_active BOOLEAN DEFAULT 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table des équipes
CREATE TABLE teams (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_by INTEGER NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (created_by) REFERENCES users(id) ON DELETE CASCADE
);

-- Table des membres d'équipe
CREATE TABLE team_members (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    team_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    role_id INTEGER DEFAULT 1,
    joined_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (team_id) REFERENCES teams(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES project_role(id),
    UNIQUE(team_id, user_id)
);

-- Table des projets
CREATE TABLE projects (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    status_id INTEGER NOT NULL DEFAULT 1,
    team_id INTEGER,
    created_by INTEGER NOT NULL,
    start_date DATE,
    end_date DATE,
    budget DECIMAL(15,2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (status_id) REFERENCES project_status(id),
    FOREIGN KEY (team_id) REFERENCES teams(id) ON DELETE SET NULL,
    FOREIGN KEY (created_by) REFERENCES users(id) ON DELETE CASCADE
);

-- Table des membres de projets
CREATE TABLE project_members (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    project_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    role_id INTEGER DEFAULT 1,
    joined_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES project_role(id),
    UNIQUE(project_id, user_id)
);

-- Table des tâches
CREATE TABLE tasks (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    status_id INTEGER NOT NULL DEFAULT 1,
    project_id INTEGER NOT NULL,
    assignee_id INTEGER,
    priority VARCHAR(20) DEFAULT 'medium' CHECK (priority IN ('low', 'medium', 'high', 'urgent')),
    estimated_hours DECIMAL(8,2),
    actual_hours DECIMAL(8,2),
    due_date DATE,
    created_by INTEGER NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (status_id) REFERENCES task_status(id),
    FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE,
    FOREIGN KEY (assignee_id) REFERENCES users(id) ON DELETE SET NULL,
    FOREIGN KEY (created_by) REFERENCES users(id) ON DELETE CASCADE
);

-- Table des commentaires
CREATE TABLE comments (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    content TEXT NOT NULL,
    task_id INTEGER,
    project_id INTEGER,
    user_id INTEGER NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (task_id) REFERENCES tasks(id) ON DELETE CASCADE,
    FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CHECK (
        (task_id IS NOT NULL AND project_id IS NULL) OR
        (task_id IS NULL AND project_id IS NOT NULL)
    )
);

-- Table des pièces jointes
CREATE TABLE attachments (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    filename VARCHAR(255) NOT NULL,
    original_name VARCHAR(255) NOT NULL,
    file_path VARCHAR(500) NOT NULL,
    file_size INTEGER NOT NULL,
    mime_type VARCHAR(100),
    task_id INTEGER,
    project_id INTEGER,
    comment_id INTEGER,
    uploaded_by INTEGER NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (task_id) REFERENCES tasks(id) ON DELETE CASCADE,
    FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE,
    FOREIGN KEY (comment_id) REFERENCES comments(id) ON DELETE CASCADE,
    FOREIGN KEY (uploaded_by) REFERENCES users(id) ON DELETE CASCADE,
    CHECK (
        (task_id IS NOT NULL AND project_id IS NULL AND comment_id IS NULL) OR
        (task_id IS NULL AND project_id IS NOT NULL AND comment_id IS NULL) OR
        (task_id IS NULL AND project_id IS NULL AND comment_id IS NOT NULL)
    )
);

-- Index pour optimiser les performances
CREATE INDEX idx_projects_status_id ON projects(status_id);
CREATE INDEX idx_projects_team_id ON projects(team_id);
CREATE INDEX idx_projects_created_by ON projects(created_by);
CREATE INDEX idx_tasks_status_id ON tasks(status_id);
CREATE INDEX idx_tasks_project_id ON tasks(project_id);
CREATE INDEX idx_tasks_assignee_id ON tasks(assignee_id);
CREATE INDEX idx_tasks_due_date ON tasks(due_date);
CREATE INDEX idx_comments_task_id ON comments(task_id);
CREATE INDEX idx_comments_project_id ON comments(project_id);
CREATE INDEX idx_comments_user_id ON comments(user_id);

-- Insertion des données de test
-- Statuts de projets
INSERT INTO project_status (name, description) VALUES
('Planning', 'Projet en phase de planification'),
('In Progress', 'Projet en cours de développement'),
('On Hold', 'Projet en pause'),
('Completed', 'Projet terminé'),
('Cancelled', 'Projet annulé');

-- Statuts de tâches
INSERT INTO task_status (name, description, color) VALUES
('To Do', 'Tâche à faire', '#6c757d'),
('In Progress', 'Tâche en cours', '#007bff'),
('In Review', 'Tâche en révision', '#ffc107'),
('Testing', 'Tâche en test', '#fd7e14'),
('Done', 'Tâche terminée', '#28a745'),
('Blocked', 'Tâche bloquée', '#dc3545');

-- Rôles
INSERT INTO project_role (name, description, permissions) VALUES
('Owner', 'Propriétaire du projet', 'all'),
('Admin', 'Administrateur', 'manage_project,manage_members,manage_tasks'),
('Developer', 'Développeur', 'view_project,create_tasks,update_own_tasks,comment'),
('Viewer', 'Observateur', 'view_project,comment');

-- Utilisateurs de test
INSERT INTO users (email, password, first_name, last_name, avatar_url) VALUES
('john.doe@codesolutions.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBaLbVfaO0d2tO', 'John', 'Doe', NULL),
('jane.smith@codesolutions.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBaLbVfaO0d2tO', 'Jane', 'Smith', NULL),
('mike.johnson@codesolutions.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBaLbVfaO0d2tO', 'Mike', 'Johnson', NULL),
('sarah.wilson@codesolutions.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBaLbVfaO0d2tO', 'Sarah', 'Wilson', NULL);

-- Équipes de test
INSERT INTO teams (name, description, created_by) VALUES
('Development Team', 'Équipe de développement principal', 1),
('Design Team', 'Équipe de design et UX', 2);

-- Membres d'équipe
INSERT INTO team_members (team_id, user_id, role_id) VALUES
(1, 1, 1), -- John Doe - Owner of Dev Team
(1, 3, 2), -- Mike Johnson - Admin of Dev Team
(1, 4, 3), -- Sarah Wilson - Developer in Dev Team
(2, 2, 1), -- Jane Smith - Owner of Design Team
(2, 4, 3); -- Sarah Wilson - Developer in Design Team also

-- Projets de test
INSERT INTO projects (name, description, status_id, team_id, created_by, start_date, end_date) VALUES
('PMT Platform', 'Développement de la plateforme de gestion de projet PMT', 2, 1, 1, '2025-01-01', '2025-06-30'),
('Website Redesign', 'Refonte du site web de l''entreprise', 1, 2, 2, '2025-02-01', '2025-04-30'),
('Mobile App', 'Développement de l''application mobile PMT', 1, 1, 3, '2025-03-01', '2025-08-31');

-- Membres de projets
INSERT INTO project_members (project_id, user_id, role_id) VALUES
(1, 1, 1), -- John - Owner of PMT Platform
(1, 3, 2), -- Mike - Admin of PMT Platform
(1, 4, 3), -- Sarah - Developer on PMT Platform
(2, 2, 1), -- Jane - Owner of Website Redesign
(2, 4, 3), -- Sarah - Developer on Website Redesign
(3, 3, 1), -- Mike - Owner of Mobile App
(3, 1, 3); -- John - Developer on Mobile App

-- Tâches de test
INSERT INTO tasks (title, description, status_id, project_id, assignee_id, priority, estimated_hours, due_date, created_by) VALUES
('Configuration backend Spring Boot', 'Initialiser le projet Spring Boot avec les dépendances nécessaires', 2, 1, 1, 'high', 16, '2025-01-15', 1),
('Conception base de données', 'Créer le schéma de base de données avec les entités principales', 5, 1, 1, 'high', 12, '2025-01-10', 1),
('Configuration Angular', 'Initialiser le projet Angular avec routing et composants de base', 1, 1, 3, 'medium', 8, '2025-01-20', 1),
('Interface d''authentification', 'Créer les pages de login et inscription', 1, 1, 4, 'medium', 20, '2025-02-01', 3),
('API REST utilisateurs', 'Implémenter les endpoints CRUD pour les utilisateurs', 1, 1, 1, 'high', 16, '2025-01-25', 1),
('API REST projets', 'Implémenter les endpoints CRUD pour les projets', 1, 1, 3, 'high', 16, '2025-01-30', 1),
('Tests unitaires backend', 'Écrire les tests unitaires pour les services backend', 1, 1, 1, 'medium', 24, '2025-03-01', 1),
('Tests frontend', 'Configurer et écrire les tests pour les composants Angular', 1, 1, 4, 'medium', 20, '2025-03-15', 1);

-- Commentaires de test
INSERT INTO comments (content, task_id, user_id) VALUES
('Le schéma de base de données a été finalisé et validé', 2, 1),
('J''ai commencé l''implémentation du backend Spring Boot', 1, 1),
('Les tests unitaires sont en cours de rédaction', 7, 1),
('Interface d''authentification terminée, en attente de review', 4, 4);
