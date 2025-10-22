import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Team } from '../../models';

@Component({
  selector: 'app-teams',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="teams">
      <div class="teams-header">
        <h2>Teams</h2>
        <button class="btn-primary">New Team</button>
      </div>

      <div class="teams-grid">
        <div class="team-card" *ngFor="let team of teams">
          <div class="team-header">
            <h3>{{ team.name }}</h3>
          </div>

          <div class="team-description">
            {{ team.description || 'No description available' }}
          </div>

          <div class="team-meta">
            <div class="meta-item">
              <span class="meta-label">Created by:</span>
              <span class="meta-value">{{ team.createdByName }}</span>
            </div>
            <div class="meta-item">
              <span class="meta-label">Members:</span>
              <span class="meta-value">{{ getTeamMembersCount(team.id) }}</span>
            </div>
          </div>

          <div class="team-actions">
            <button class="btn-secondary">View Members</button>
            <button class="btn-secondary">Edit</button>
          </div>
        </div>
      </div>
    </div>
  `,
  styleUrls: ['./teams.component.scss']
})
export class TeamsComponent implements OnInit {
  teams: Team[] = [
    {
      id: 1,
      name: 'Development Team',
      description: 'Équipe de développement principal',
      createdById: 1,
      createdByName: 'John Doe',
      createdAt: '2025-01-01T00:00:00',
      updatedAt: '2025-01-01T00:00:00'
    },
    {
      id: 2,
      name: 'Design Team',
      description: 'Équipe de design et UX',
      createdById: 2,
      createdByName: 'Jane Smith',
      createdAt: '2025-01-01T00:00:00',
      updatedAt: '2025-01-01T00:00:00'
    }
  ];

  ngOnInit() {
    // TODO: Load teams from service
  }

  getTeamMembersCount(teamId: number): number {
    // Mock data - in real app this would come from service
    const membersCount = {
      1: 4,
      2: 2
    };
    return membersCount[teamId] || 0;
  }
}
