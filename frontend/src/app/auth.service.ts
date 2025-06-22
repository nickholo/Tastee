import { Injectable } from '@angular/core';
import { Observable, of, throwError } from 'rxjs';

@Injectable({
	providedIn: 'root',
})
export class AuthService {
	constructor() {}

	private readonly mockUser = {
		email: 'test@example.com',
		password: 'password123',
	};

	login(email: string, password: string): Observable<{ token: string }> {
		if (
			email === this.mockUser.email &&
			password === this.mockUser.password
		) {
			const fakeToken = 'mock-jwt-token';
			localStorage.setItem('token', fakeToken);
			return of({ token: fakeToken });
		} else {
			return throwError(() => new Error('Invalid credentials'));
		}
	}

	register(email: string, password: string): Observable<{ message: string }> {
		// You can expand this to store in memory if needed
		console.log('User registered:', { email, password });
		return of({ message: 'User registered successfully (mock)' });
	}

	logout(): void {
		localStorage.removeItem('token');
	}

	isAuthenticated(): boolean {
		return !!localStorage.getItem('token');
	}
}
