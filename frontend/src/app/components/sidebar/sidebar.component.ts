import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [RouterLink, RouterLinkActive],
  template: `
    <nav class="sidebar">
      <div class="sidebar-header">
        <h3>Navigation</h3>
      </div>

      <ul class="nav-menu">
        <li class="nav-item">
          <a routerLink="/dashboard" routerLinkActive="active" class="nav-link">
            <span class="nav-icon">📊</span>
            Dashboard
          </a>
        </li>

        <li class="nav-item">
          <a routerLink="/projects" routerLinkActive="active" class="nav-link">
            <span class="nav-icon">📁</span>
            Projects
          </a>
        </li>

        <li class="nav-item">
          <a routerLink="/tasks" routerLinkActive="active" class="nav-link">
            <span class="nav-icon">✅</span>
            Tasks
          </a>
        </li>

        <li class="nav-item">
          <a routerLink="/teams" routerLinkActive="active" class="nav-link">
            <span class="nav-icon">👥</span>
            Teams
          </a>
        </li>

        <li class="nav-item">
          <a routerLink="/users" routerLinkActive="active" class="nav-link">
            <span class="nav-icon">👤</span>
            Users
          </a>
        </li>
      </ul>

      <div class="sidebar-footer">
        <div class="version-info">
          PMT v0.0.1
        </div>
      </div>
    </nav>
  `,
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent {
}
