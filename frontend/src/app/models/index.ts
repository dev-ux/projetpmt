export interface User {
  id: number;
  email: string;
  firstName: string;
  lastName: string;
  avatarUrl?: string;
  isActive: boolean;
  createdAt: string;
  updatedAt: string;
}

export interface Project {
  id: number;
  name: string;
  description?: string;
  statusId: number;
  statusName: string;
  teamId?: number;
  teamName?: string;
  createdById: number;
  createdByName: string;
  startDate?: string;
  endDate?: string;
  budget?: number;
  createdAt: string;
  updatedAt: string;
}

export interface Task {
  id: number;
  title: string;
  description?: string;
  statusId: number;
  statusName: string;
  statusColor: string;
  projectId: number;
  projectName: string;
  assigneeId?: number;
  assigneeName?: string;
  priority: TaskPriority;
  estimatedHours?: number;
  actualHours?: number;
  dueDate?: string;
  createdById: number;
  createdByName: string;
  createdAt: string;
  updatedAt: string;
}

export interface Team {
  id: number;
  name: string;
  description?: string;
  createdById: number;
  createdByName: string;
  createdAt: string;
  updatedAt: string;
}

export interface Comment {
  id: number;
  content: string;
  taskId?: number;
  taskTitle?: string;
  projectId?: number;
  projectName?: string;
  authorId: number;
  authorName: string;
  createdAt: string;
  updatedAt: string;
}

export interface Attachment {
  id: number;
  filename: string;
  originalName: string;
  filePath: string;
  fileSize: number;
  mimeType?: string;
  taskId?: number;
  taskTitle?: string;
  projectId?: number;
  projectName?: string;
  commentId?: number;
  uploadedById: number;
  uploadedByName: string;
  createdAt: string;
}

export enum TaskPriority {
  LOW = 'LOW',
  MEDIUM = 'MEDIUM',
  HIGH = 'HIGH',
  URGENT = 'URGENT'
}

export interface ProjectStatus {
  id: number;
  name: string;
  description?: string;
  createdAt: string;
  updatedAt: string;
}

export interface TaskStatus {
  id: number;
  name: string;
  description?: string;
  color: string;
  createdAt: string;
  updatedAt: string;
}

export interface ProjectRole {
  id: number;
  name: string;
  description?: string;
  permissions?: string;
  createdAt: string;
  updatedAt: string;
}
