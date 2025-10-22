import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { User } from '../models';

export interface UserDTO {
  id: number;
  email: string;
  firstName: string;
  lastName: string;
  avatarUrl?: string;
  isActive: boolean;
  createdAt: string;
  updatedAt: string;
}

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private endpoint = '/users';

  constructor(private apiService: ApiService) {}

  getAllUsers(): Observable<UserDTO[]> {
    return this.apiService.get<UserDTO[]>(this.endpoint);
  }

  getActiveUsers(): Observable<UserDTO[]> {
    return this.apiService.get<UserDTO[]>(`${this.endpoint}/active`);
  }

  getUserById(id: number): Observable<UserDTO> {
    return this.apiService.get<UserDTO>(`${this.endpoint}/${id}`);
  }

  getUserByEmail(email: string): Observable<UserDTO> {
    return this.apiService.get<UserDTO>(`${this.endpoint}/email/${email}`);
  }

  createUser(user: Partial<UserDTO>): Observable<UserDTO> {
    return this.apiService.post<UserDTO>(this.endpoint, user);
  }

  updateUser(id: number, user: Partial<UserDTO>): Observable<UserDTO> {
    return this.apiService.put<UserDTO>(`${this.endpoint}/${id}`, user);
  }

  deleteUser(id: number): Observable<void> {
    return this.apiService.delete<void>(`${this.endpoint}/${id}`);
  }

  deactivateUser(id: number): Observable<void> {
    return this.apiService.patch<void>(`${this.endpoint}/${id}/deactivate`, {});
  }

  activateUser(id: number): Observable<void> {
    return this.apiService.patch<void>(`${this.endpoint}/${id}/activate`, {});
  }

  searchUsers(query: string): Observable<UserDTO[]> {
    const params = new URLSearchParams({ query });
    return this.apiService.get<UserDTO[]>(`${this.endpoint}/search?${params}`);
  }

  checkEmailAvailability(email: string): Observable<boolean> {
    const params = new URLSearchParams({ email });
    return this.apiService.get<boolean>(`${this.endpoint}/check-email?${params}`);
  }
}
