import { CommonModule } from '@angular/common';
import { Component, OnInit, Input } from '@angular/core';
import { IonicModule } from '@ionic/angular';

@Component({
	selector: 'app-recipe-card',
	templateUrl: './recipe-card.component.html',
	styleUrls: ['./recipe-card.component.scss'],
	imports: [IonicModule, CommonModule],
})
export class RecipeCardComponent implements OnInit {
	constructor() {}

	ngOnInit() {}

	@Input() recipe: any;
	isFlipped = false;

	toggleFlip() {
		this.isFlipped = !this.isFlipped;
	}
}
