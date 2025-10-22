import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { User } from '../../models';

@Component({
  selector: 'app-users',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="users">
      <div class="users-header">
        <h2>Users</h2>
        <button class="btn-primary">New User</button>
      </div>

      <div class="users-list">
        <div class="user-card" *ngFor="let user of users">
          <div class="user-avatar">
            {{ getInitials(user.firstName, user.lastName) }}
          </div>

          <div class="user-info">
            <h4>{{ user.firstName }} {{ user.lastName }}</h4>
            <p class="user-email">{{ user.email }}</p>
          </div>

          <div class="user-status">
            <span class="status-badge" [ngClass]="user.isActive ? 'status-active' : 'status-inactive'">
              {{ user.isActive ? 'Active' : 'Inactive' }}
            </span>
          </div>

          <div class="user-actions">
            <button class="btn-secondary">Edit</button>
            <button class="btn-secondary" [ngClass]="user.isActive ? 'btn-deactivate' : 'btn-activate'"
                    (click)="toggleUserStatus(user)">
              {{ user.isActive ? 'Deactivate' : 'Activate' }}
            </button>
          </div>
        </div>
      </div>
    </div>
  `,
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {
  users: User[] = [
    {
      id: 1,
      email: 'john.doe@codesolutions.com',
      firstName: 'John',
      lastName: 'Doe',
      isActive: true,
      createdAt: '2025-01-01T00:00:00',
      updatedAt: '2025-01-01T00:00:00'
    },
    {
      id: 2,
      email: 'jane.smith@codesolutions.com',
      firstName: 'Jane',
      lastName: 'Smith',
      isActive: true,
      createdAt: '2025-01-01T00:00:00',
      updatedAt: '2025-01-01T00:00:00'
    },
    {
      id: 3,
      email: 'mike.johnson@codesolutions.com',
      firstName: 'Mike',
      lastName: 'Johnson',
      isActive: true,
      createdAt: '2025-01-01T00:00:00',
      updatedAt: '2025-01-01T00:00:00'
    },
    {
      id: 4,
      email: 'sarah.wilson@codesolutions.com',
      firstName: 'Sarah',
      lastName: 'Wilson',
      isActive: true,
      createdAt: '2025-01-01T00:00:00',
      updatedAt: '2025-01-01T00:00:00'
    }
  ];

  ngOnInit() {
    // TODO: Load users from service
  }

  getInitials(firstName: string, lastName: string): string {
    return (firstName.charAt(0) + lastName.charAt(0)).toUpperCase();
  }

  toggleUserStatus(user: User): void {
    user.isActive = !user.isActive;
    // TODO: Call service to update user status
  }
}
