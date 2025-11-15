import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { IonCard, IonCardContent, IonCardHeader, IonCardSubtitle, IonCardTitle } from '@ionic/angular/standalone';
import { Feed } from '../feed';
import { NgOptimizedImage } from "@angular/common";
@Component({
  selector: 'app-recipe-card',
  templateUrl: './recipe-card.component.html',
  styleUrls: ['./recipe-card.component.scss'],
  imports: [IonCard, IonCardContent, IonCardHeader, IonCardTitle, IonCardSubtitle, NgOptimizedImage],
})
export class RecipeCardComponent  implements OnInit {

  @Input() cardId!: number;
  @Input() imgUrl!: string;
  @Output() swiped = new EventEmitter<{ action: 'like' | 'dislike', cardId: number }>;
  animation: boolean = false;

  constructor() { }

  ngOnInit() {
    console.log('RecipeCardComponent initialized', this.cardId, this.imgUrl);
    
  }
  handleCardClick() {
    console.log('Recipe card clicked!');
    this.swiped.emit({ action: 'like', cardId: this.cardId });
    this.animation = true;
  }

}
