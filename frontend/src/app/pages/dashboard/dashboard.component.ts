import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  template: `
    <div class="dashboard">
      <div class="dashboard-header">
        <h2>Dashboard</h2>
        <p class="subtitle">Welcome to PMT - Project Management Tool</p>
      </div>

      <div class="stats-grid">
        <div class="stat-card">
          <div class="stat-icon">📊</div>
          <div class="stat-content">
            <h3>{{ totalProjects }}</h3>
            <p>Total Projects</p>
          </div>
        </div>

        <div class="stat-card">
          <div class="stat-icon">✅</div>
          <div class="stat-content">
            <h3>{{ totalTasks }}</h3>
            <p>Total Tasks</p>
          </div>
        </div>

        <div class="stat-card">
          <div class="stat-icon">👥</div>
          <div class="stat-content">
            <h3>{{ totalUsers }}</h3>
            <p>Team Members</p>
          </div>
        </div>

        <div class="stat-card">
          <div class="stat-icon">⏱️</div>
          <div class="stat-content">
            <h3>{{ activeTasks }}</h3>
            <p>Active Tasks</p>
          </div>
        </div>
      </div>

      <div class="recent-activity">
        <h3>Recent Activity</h3>
        <div class="activity-list">
          <div class="activity-item">
            <div class="activity-icon">📝</div>
            <div class="activity-content">
              <p><strong>John Doe</strong> created a new task "Configure backend"</p>
              <small>2 hours ago</small>
            </div>
          </div>

          <div class="activity-item">
            <div class="activity-icon">📁</div>
            <div class="activity-content">
              <p><strong>Jane Smith</strong> created project "Website Redesign"</p>
              <small>1 day ago</small>
            </div>
          </div>

          <div class="activity-item">
            <div class="activity-icon">✅</div>
            <div class="activity-content">
              <p><strong>Mike Johnson</strong> completed task "Database design"</p>
              <small>2 days ago</small>
            </div>
          </div>
        </div>
      </div>
    </div>
  `,
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  totalProjects = 3;
  totalTasks = 8;
  totalUsers = 4;
  activeTasks = 5;

  ngOnInit() {
    // TODO: Load actual data from services
  }
}
