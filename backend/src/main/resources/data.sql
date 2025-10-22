-- Insert default project statuses
INSERT INTO project_status (name, description) VALUES
('Planning', 'Projet en phase de planification'),
('In Progress', 'Projet en cours de développement'),
('On Hold', 'Projet en pause'),
('Completed', 'Projet terminé'),
('Cancelled', 'Projet annulé');

-- Insert default task statuses
INSERT INTO task_status (name, description, color) VALUES
('To Do', 'Tâche à faire', '#6c757d'),
('In Progress', 'Tâche en cours', '#007bff'),
('In Review', 'Tâche en révision', '#ffc107'),
('Testing', 'Tâche en test', '#fd7e14'),
('Done', 'Tâche terminée', '#28a745'),
('Blocked', 'Tâche bloquée', '#dc3545');

-- Insert default project roles
INSERT INTO project_role (name, description, permissions) VALUES
('Owner', 'Propriétaire du projet', 'all'),
('Admin', 'Administrateur', 'manage_project,manage_members,manage_tasks'),
('Developer', 'Développeur', 'view_project,create_tasks,update_own_tasks,comment'),
('Viewer', 'Observateur', 'view_project,comment');

-- Insert test users (passwords are hashed with BCrypt)
INSERT INTO users (email, password, first_name, last_name, avatar_url, is_active) VALUES
('john.doe@codesolutions.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBaLbVfaO0d2tO', 'John', 'Doe', NULL, 1),
('jane.smith@codesolutions.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBaLbVfaO0d2tO', 'Jane', 'Smith', NULL, 1),
('mike.johnson@codesolutions.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBaLbVfaO0d2tO', 'Mike', 'Johnson', NULL, 1),
('sarah.wilson@codesolutions.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBaLbVfaO0d2tO', 'Sarah', 'Wilson', NULL, 1);

-- Insert test teams
INSERT INTO teams (name, description, created_by) VALUES
('Development Team', 'Équipe de développement principal', 1),
('Design Team', 'Équipe de design et UX', 2);

-- Insert team members
INSERT INTO team_members (team_id, user_id, role_id) VALUES
(1, 1, 1), -- John Doe - Owner of Dev Team
(1, 3, 2), -- Mike Johnson - Admin of Dev Team
(1, 4, 3), -- Sarah Wilson - Developer in Dev Team
(2, 2, 1), -- Jane Smith - Owner of Design Team
(2, 4, 3); -- Sarah Wilson - Developer in Design Team also

-- Insert test projects
INSERT INTO projects (name, description, status_id, team_id, created_by, start_date, end_date, budget) VALUES
('PMT Platform', 'Développement de la plateforme de gestion de projet PMT', 2, 1, 1, '2025-01-01', '2025-06-30', 50000.00),
('Website Redesign', 'Refonte du site web de l''entreprise', 1, 2, 2, '2025-02-01', '2025-04-30', 25000.00),
('Mobile App', 'Développement de l''application mobile PMT', 1, 1, 3, '2025-03-01', '2025-08-31', 75000.00);

-- Insert project members
INSERT INTO project_members (project_id, user_id, role_id) VALUES
(1, 1, 1), -- John - Owner of PMT Platform
(1, 3, 2), -- Mike - Admin of PMT Platform
(1, 4, 3), -- Sarah - Developer on PMT Platform
(2, 2, 1), -- Jane - Owner of Website Redesign
(2, 4, 3), -- Sarah - Developer on Website Redesign
(3, 3, 1), -- Mike - Owner of Mobile App
(3, 1, 3); -- John - Developer on Mobile App

-- Insert test tasks
INSERT INTO tasks (title, description, status_id, project_id, assignee_id, priority, estimated_hours, actual_hours, due_date, created_by) VALUES
('Configuration backend Spring Boot', 'Initialiser le projet Spring Boot avec les dépendances nécessaires', 2, 1, 1, 'HIGH', 16.00, 12.00, '2025-01-15', 1),
('Conception base de données', 'Créer le schéma de base de données avec les entités principales', 5, 1, 1, 'HIGH', 12.00, 12.00, '2025-01-10', 1),
('Configuration Angular', 'Initialiser le projet Angular avec routing et composants de base', 1, 1, 3, 'MEDIUM', 8.00, NULL, '2025-01-20', 1),
('Interface d''authentification', 'Créer les pages de login et inscription', 1, 1, 4, 'MEDIUM', 20.00, NULL, '2025-02-01', 3),
('API REST utilisateurs', 'Implémenter les endpoints CRUD pour les utilisateurs', 1, 1, 1, 'HIGH', 16.00, NULL, '2025-01-25', 1),
('API REST projets', 'Implémenter les endpoints CRUD pour les projets', 1, 1, 3, 'HIGH', 16.00, NULL, '2025-01-30', 1),
('Tests unitaires backend', 'Écrire les tests unitaires pour les services backend', 1, 1, 1, 'MEDIUM', 24.00, NULL, '2025-03-01', 1),
('Tests frontend', 'Configurer et écrire les tests pour les composants Angular', 1, 1, 4, 'MEDIUM', 20.00, NULL, '2025-03-15', 1),
('Design système', 'Créer la maquette des interfaces utilisateur', 2, 2, 2, 'HIGH', 40.00, 35.00, '2025-02-15', 2),
('Intégration responsive', 'Adapter le design pour mobile et tablette', 1, 2, 4, 'MEDIUM', 16.00, NULL, '2025-03-01', 2);

-- Insert test comments
INSERT INTO comments (content, task_id, user_id) VALUES
('Le schéma de base de données a été finalisé et validé', 2, 1),
('J''ai commencé l''implémentation du backend Spring Boot', 1, 1),
('Les tests unitaires sont en cours de rédaction', 7, 1),
('Interface d''authentification terminée, en attente de review', 4, 4),
('Design validé par le client, prêt pour l''intégration', 9, 2);
