import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';
import { HttpClient } from '@angular/common/http';
import {
	IonContent,
	IonHeader,
	IonTitle,
	IonToolbar,
	IonButton,
	IonLabel,
	IonItem,
} from '@ionic/angular/standalone';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth.service';

@Component({
	selector: 'app-login',
	templateUrl: './login.page.html',
	styleUrls: ['./login.page.scss'],
	standalone: true,
	imports: [IonicModule, CommonModule, FormsModule],
})
export class LoginPage implements OnInit {
	email: string = '';
	password: string = '';
	errorMessage = '';

	constructor(
		private http: HttpClient,
		private router: Router,
		private authService: AuthService
	) {}

	ngOnInit() {}

	login() {
		this.authService.login(this.email, this.password).subscribe({
			next: () => this.router.navigate(['/swipe']),
			error: (err) => (this.errorMessage = err.message),
		});
		// this.http
		// 	.post('https://yourapi.com/login', {
		// 		email: this.email,
		// 		password: this.password,
		// 	})
		// 	.subscribe(
		// 		(res: any) => {
		// 			localStorage.setItem('token', res.token); // save JWT
		// 			this.router.navigate(['/swipe']); // go to main page
		// 		},
		// 		(error) => {
		// 			console.error('Login failed', error);
		// 		}
		// 	);
	}
}
