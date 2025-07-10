import { Component } from '@angular/core';
import { IonicModule } from '@ionic/angular';
import { AuthService } from 'src/app/auth.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
	selector: 'app-home',
	templateUrl: 'home.page.html',
	styleUrls: ['home.page.scss'],
	imports: [
		IonicModule, CommonModule
	],
})
export class HomePage {

	isDarkMode = false;

	constructor(private auth: AuthService, private router: Router) {
  	}

	ngOnInit() {
  		if (this.auth.isAuthenticated()) {
    		this.router.navigate(['/tabs']); // Redirect after view init
  		}
	}

  	goToLogin() {
    	this.router.navigate(['/login']);
  	}

  	goToRegister() {
    	this.router.navigate(['/register']);
  	}

	  toggleTheme() {
    this.isDarkMode = !this.isDarkMode;
    document.body.classList.toggle('dark', this.isDarkMode);
  }
}
