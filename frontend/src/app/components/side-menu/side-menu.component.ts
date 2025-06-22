import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import {
	IonHeader,
	IonItem,
	IonList,
	IonMenu,
	IonTitle,
	IonToolbar,
	IonContent,
} from '@ionic/angular/standalone';

@Component({
	selector: 'app-side-menu',
	templateUrl: './side-menu.component.html',
	styleUrls: ['./side-menu.component.scss'],
	imports: [
		RouterModule,
		CommonModule,
		IonMenu,
		IonHeader,
		IonToolbar,
		IonTitle,
		IonList,
		IonItem,
		IonContent,
	],
})
export class SideMenuComponent implements OnInit {
	constructor() {}

	ngOnInit() {}
}
