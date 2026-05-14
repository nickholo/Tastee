import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs';
import { environment } from 'src/environments/environment';
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private token: string | null = null;
  private apiUrl: string = 'http://localhost:8080';

  
  constructor(private http: HttpClient){
    if (!environment.production) {
    this.token = 'dev-bypass-token'; // fake token for dev
  }
  }
  login(username: string, password: string){
    return this.http.post<{ token: string}>(`${this.apiUrl}/login`, {username, password}).pipe(
      tap( response => {
        this.token = response.token;
      })
    )
  }
  getToken(): string | null{
    return this.token;
  }
  logout() {
    this.token = null;
  }

  isLoggedIn(): boolean {
    return this.token !== null;
  }
}
