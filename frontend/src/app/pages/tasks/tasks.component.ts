import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Task, TaskPriority } from '../../models';

@Component({
  selector: 'app-tasks',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="tasks">
      <div class="tasks-header">
        <h2>Tasks</h2>
        <div class="tasks-filters">
          <select class="filter-select">
            <option value="">All Projects</option>
            <option value="1">PMT Platform</option>
            <option value="2">Website Redesign</option>
            <option value="3">Mobile App</option>
          </select>
          <select class="filter-select">
            <option value="">All Status</option>
            <option value="1">To Do</option>
            <option value="2">In Progress</option>
            <option value="3">In Review</option>
            <option value="4">Testing</option>
            <option value="5">Done</option>
          </select>
          <select class="filter-select">
            <option value="">All Priorities</option>
            <option value="LOW">Low</option>
            <option value="MEDIUM">Medium</option>
            <option value="HIGH">High</option>
            <option value="URGENT">Urgent</option>
          </select>
        </div>
        <button class="btn-primary">New Task</button>
      </div>

      <div class="tasks-list">
        <div class="task-card" *ngFor="let task of tasks">
          <div class="task-header">
            <h4>{{ task.title }}</h4>
            <span class="task-priority" [ngClass]="'priority-' + task.priority.toLowerCase()">
              {{ task.priority }}
            </span>
          </div>

          <div class="task-content">
            <p>{{ task.description || 'No description available' }}</p>
          </div>

          <div class="task-meta">
            <div class="meta-item">
              <span class="meta-label">Project:</span>
              <span class="meta-value">{{ task.projectName }}</span>
            </div>
            <div class="meta-item" *ngIf="task.assigneeName">
              <span class="meta-label">Assignee:</span>
              <span class="meta-value">{{ task.assigneeName }}</span>
            </div>
            <div class="meta-item" *ngIf="task.dueDate">
              <span class="meta-label">Due:</span>
              <span class="meta-value">{{ task.dueDate | date:'shortDate' }}</span>
            </div>
          </div>

          <div class="task-status">
            <span class="status-badge" [ngStyle]="{'background-color': task.statusColor}">
              {{ task.statusName }}
            </span>
          </div>
        </div>
      </div>
    </div>
  `,
  styleUrls: ['./tasks.component.scss']
})
export class TasksComponent implements OnInit {
  tasks: Task[] = [
    {
      id: 1,
      title: 'Configuration backend Spring Boot',
      description: 'Initialiser le projet Spring Boot avec les dépendances nécessaires',
      statusId: 2,
      statusName: 'In Progress',
      statusColor: '#007bff',
      projectId: 1,
      projectName: 'PMT Platform',
      assigneeId: 1,
      assigneeName: 'John Doe',
      priority: TaskPriority.HIGH,
      estimatedHours: 16,
      actualHours: 12,
      dueDate: '2025-01-15',
      createdById: 1,
      createdByName: 'John Doe',
      createdAt: '2025-01-01T00:00:00',
      updatedAt: '2025-01-01T00:00:00'
    },
    {
      id: 2,
      title: 'Conception base de données',
      description: 'Créer le schéma de base de données avec les entités principales',
      statusId: 5,
      statusName: 'Done',
      statusColor: '#28a745',
      projectId: 1,
      projectName: 'PMT Platform',
      priority: TaskPriority.HIGH,
      estimatedHours: 12,
      actualHours: 12,
      dueDate: '2025-01-10',
      createdById: 1,
      createdByName: 'John Doe',
      createdAt: '2025-01-01T00:00:00',
      updatedAt: '2025-01-01T00:00:00'
    },
    {
      id: 3,
      title: 'Configuration Angular',
      description: 'Initialiser le projet Angular avec routing et composants de base',
      statusId: 1,
      statusName: 'To Do',
      statusColor: '#6c757d',
      projectId: 1,
      projectName: 'PMT Platform',
      assigneeId: 3,
      assigneeName: 'Mike Johnson',
      priority: TaskPriority.MEDIUM,
      estimatedHours: 8,
      dueDate: '2025-01-20',
      createdById: 1,
      createdByName: 'John Doe',
      createdAt: '2025-01-01T00:00:00',
      updatedAt: '2025-01-01T00:00:00'
    }
  ];

  ngOnInit() {
    // TODO: Load tasks from service
  }
}
