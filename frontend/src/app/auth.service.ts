import { Injectable } from '@angular/core';
import { Observable, of, throwError } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private isLoggedIn = false;

  login(email: string, password: string): Observable<{ token: string }> {
    if (email === 'test@example.com' && password === 'password123') {
      this.isLoggedIn = true;
      localStorage.setItem('token', 'mock-token');
      return of({ token: 'mock-token' });
    }
    return throwError(() => new Error('Invalid credentials'));
  }

  register(email: string, password: string): Observable<{ message: string }> {
    this.isLoggedIn = true;
    localStorage.setItem('token', 'mock-token');
    return of({ message: 'Registered successfully (mock)' });
  }

  logout(): void {
    this.isLoggedIn = false;
    localStorage.removeItem('token');
  }

  isAuthenticated(): boolean {
    return !!localStorage.getItem('token');
  }
}