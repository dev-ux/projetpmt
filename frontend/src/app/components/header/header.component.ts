import { Component } from '@angular/core';

@Component({
  selector: 'app-header',
  standalone: true,
  template: `
    <header class="app-header">
      <div class="header-left">
        <h1 class="app-title">PMT</h1>
        <span class="app-subtitle">Project Management Tool</span>
      </div>
      <div class="header-right">
        <div class="user-menu">
          <span class="user-name">John Doe</span>
          <div class="user-avatar">JD</div>
        </div>
      </div>
    </header>
  `,
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
}
