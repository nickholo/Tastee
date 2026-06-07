import { Component } from '@angular/core';
import {
  IonHeader,
  IonToolbar,
  IonTitle,
  IonContent,
  IonCard,
  IonCardHeader,
  IonButton,
  IonInput,
  IonLabel,
  IonItem,
  IonCardContent,
  IonCardTitle,
} from '@ionic/angular/standalone';
import { ExploreContainerComponent } from '../../explore-container/explore-container.component';
import { FormControl, FormGroup } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-tab2',
  templateUrl: 'tab2.page.html',
  styleUrls: ['tab2.page.scss'],
  imports: [
    IonHeader,
    IonToolbar,
    IonTitle,
    IonContent,
    ExploreContainerComponent,
    IonCard,
    IonCardHeader,
    IonButton,
    IonInput,
    IonLabel,
    IonItem,
    IonCardContent,
    IonCardTitle,
    FormsModule,
    ReactiveFormsModule,
  ],
})
export class Tab2Page {
  postForm = new FormGroup({
    title: new FormControl(''),
    imgUrl: new FormControl(''),
    description: new FormControl(''),
    calories: new FormControl(''),
  });
  constructor() {}
}
