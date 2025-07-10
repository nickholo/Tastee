import { CommonModule } from '@angular/common';
import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { RouterModule } from '@angular/router';
import { IonicModule } from '@ionic/angular';

@Component({
	selector: 'app-side-menu',
	templateUrl: './side-menu.component.html',
	styleUrls: ['./side-menu.component.scss'],
	imports: [
		RouterModule,
		CommonModule,
		IonicModule,
	],
})
export class SideMenuComponent implements OnInit {
	constructor() {}

	ngOnInit() {}

	@Output() logout = new EventEmitter<void>();
}
