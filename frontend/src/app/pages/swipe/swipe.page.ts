import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {
	IonContent,
	IonHeader,
	IonTitle,
	IonToolbar,
	IonMenuButton,
	IonButtons
} from '@ionic/angular/standalone';
import { RecipeCardComponent } from 'src/app/components/recipe-card/recipe-card.component';
import { AuthService } from 'src/app/auth.service';
import { Router } from '@angular/router';

@Component({
	selector: 'app-swipe',
	templateUrl: './swipe.page.html',
	styleUrls: ['./swipe.page.scss'],
	standalone: true,
	imports: [
		IonContent,
		IonHeader,
		IonTitle,
		IonToolbar,
		IonMenuButton,
		IonButtons,
		CommonModule,
		FormsModule,
		RecipeCardComponent,
	],
})
export class SwipePage {
	constructor(auth: AuthService, router: Router) {
    if (!auth.isAuthenticated()) router.navigate(['/login']);
  }
}
