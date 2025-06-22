import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {
	IonContent,
	IonHeader,
	IonTitle,
	IonToolbar,
} from '@ionic/angular/standalone';
import { RecipeCardComponent } from 'src/app/components/recipe-card/recipe-card.component';

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
		CommonModule,
		FormsModule,
		RecipeCardComponent,
	],
})
export class SwipePage implements OnInit {
	constructor() {}

	ngOnInit() {}
}
