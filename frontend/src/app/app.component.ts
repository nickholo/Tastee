import { Component } from '@angular/core';
import { IonApp, IonRouterOutlet } from '@ionic/angular/standalone';
import { SideMenuComponent } from './components/side-menu/side-menu.component';
import { AuthService } from './auth.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
	selector: 'app-root',
	templateUrl: 'app.component.html',
	imports: [IonApp, IonRouterOutlet, SideMenuComponent, CommonModule],
})
export class AppComponent {
	constructor(public authService: AuthService, private router: Router) {}

	logout() {
    	this.authService.logout();
    	this.router.navigate(['/login']);
	}
}
