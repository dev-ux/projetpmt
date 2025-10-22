import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Project } from '../../models';

@Component({
  selector: 'app-projects',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="projects">
      <div class="projects-header">
        <h2>Projects</h2>
        <button class="btn-primary">New Project</button>
      </div>

      <div class="projects-grid">
        <div class="project-card" *ngFor="let project of projects">
          <div class="project-header">
            <h3>{{ project.name }}</h3>
            <span class="project-status" [ngClass]="'status-' + project.statusName.toLowerCase().replace(' ', '-')">
              {{ project.statusName }}
            </span>
          </div>

          <div class="project-description">
            {{ project.description || 'No description available' }}
          </div>

          <div class="project-meta">
            <div class="meta-item">
              <span class="meta-label">Team:</span>
              <span class="meta-value">{{ project.teamName || 'No team' }}</span>
            </div>
            <div class="meta-item">
              <span class="meta-label">Created by:</span>
              <span class="meta-value">{{ project.createdByName }}</span>
            </div>
            <div class="meta-item" *ngIf="project.budget">
              <span class="meta-label">Budget:</span>
              <span class="meta-value">${{ project.budget | number:'1.0-0' }}</span>
            </div>
          </div>

          <div class="project-actions">
            <button class="btn-secondary">View Details</button>
            <button class="btn-secondary">Edit</button>
          </div>
        </div>
      </div>
    </div>
  `,
  styleUrls: ['./projects.component.scss']
})
export class ProjectsComponent implements OnInit {
  projects: Project[] = [
    {
      id: 1,
      name: 'PMT Platform',
      description: 'Développement de la plateforme de gestion de projet PMT',
      statusId: 2,
      statusName: 'In Progress',
      teamId: 1,
      teamName: 'Development Team',
      createdById: 1,
      createdByName: 'John Doe',
      budget: 50000,
      createdAt: '2025-01-01T00:00:00',
      updatedAt: '2025-01-01T00:00:00'
    },
    {
      id: 2,
      name: 'Website Redesign',
      description: 'Refonte du site web de l\'entreprise',
      statusId: 1,
      statusName: 'Planning',
      teamId: 2,
      teamName: 'Design Team',
      createdById: 2,
      createdByName: 'Jane Smith',
      budget: 25000,
      createdAt: '2025-01-01T00:00:00',
      updatedAt: '2025-01-01T00:00:00'
    },
    {
      id: 3,
      name: 'Mobile App',
      description: 'Développement de l\'application mobile PMT',
      statusId: 1,
      statusName: 'Planning',
      teamId: 1,
      teamName: 'Development Team',
      createdById: 3,
      createdByName: 'Mike Johnson',
      budget: 75000,
      createdAt: '2025-01-01T00:00:00',
      updatedAt: '2025-01-01T00:00:00'
    }
  ];

  ngOnInit() {
    // TODO: Load projects from service
  }
}
