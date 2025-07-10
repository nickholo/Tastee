import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth.service';

@Component({
	selector: 'app-login',
	templateUrl: './login.page.html',
	styleUrls: ['./login.page.scss'],
	standalone: true,
	imports: [IonicModule, CommonModule, FormsModule],
})
export class LoginPage {
	email = '';
  password = '';
  error = '';

  constructor(private auth: AuthService, private router: Router) {
    if (auth.isAuthenticated()) router.navigate(['/home']);
  }

  login() {
    this.auth.login(this.email, this.password).subscribe({
      next: () => this.router.navigate(['/home']),
      error: (err) => this.error = err.message
    });
  }
}
