import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { IonCard, IonCardContent, IonCardHeader, IonCardSubtitle, IonCardTitle } from '@ionic/angular/standalone';
import { Feed } from '../../../feed-service';
import { NgOptimizedImage } from "@angular/common";
import { Card } from 'src/app/Card';
@Component({
  selector: 'app-recipe-card',
  templateUrl: './recipe-card.component.html',
  styleUrls: ['./recipe-card.component.scss'],
  imports: [IonCard, IonCardContent, IonCardHeader, IonCardTitle, IonCardSubtitle, NgOptimizedImage],
})
export class RecipeCardComponent  implements OnInit {

  @Input() card!: Card;
  @Output() swiped = new EventEmitter<{ action: 'like' | 'dislike', cardId: number }>;
  animation: boolean = false;

  constructor() {
    // console.log("Card within Constructor: " +this.card)
   }

  ngOnInit() {
    
  }
  handleCardClick() {
    console.log('Recipe card clicked!');
    this.animation = true;
  }
  onAnimationEnd(){
    this.swiped.emit({ action: 'like', cardId: this.card.cardId });
    console.log("Animation Ended")
  }

}
