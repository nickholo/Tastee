import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import {
  IonCard,
  IonCardContent,
  IonCardHeader,
  IonCardSubtitle,
  IonCardTitle,
  IonButton,
  IonIcon,
} from '@ionic/angular/standalone';
import { heartOutline, heartDislikeOutline } from 'ionicons/icons';
import { Card } from 'src/app/Card';
import { Author } from 'src/app/author';
import { addIcons } from 'ionicons';
import { InteractionService } from 'src/app/interaction-service';
@Component({
  selector: 'app-recipe-card',
  templateUrl: './recipe-card.component.html',
  styleUrls: ['./recipe-card.component.scss'],
  imports: [
    IonCard,
    IonCardContent,
    IonCardHeader,
    IonCardTitle,
    IonCardSubtitle,
    IonButton,
    IonIcon,
  ],
})
export class RecipeCardComponent implements OnInit {
  animation: 'like' | 'dislike' | null = null;
  private pendingAction: 'like' | 'dislike' | null = null;

  @Input() card!: Card;
  @Output() swiped = new EventEmitter<{
    action: 'like' | 'dislike';
    cardId: number;
  }>();


  constructor(private interaction: InteractionService) {
    // console.log("Card within Constructor: " +this.card)
    addIcons({ heartOutline, heartDislikeOutline });
  }

  ngOnInit() {

    // Trace
    console.log("Card Initalized: ", this.card)
  }
  handleCardClick() {
    console.log('Recipe card clicked!');
    this.likeCard();
  }
  onAnimationEnd() {
    if (!this.pendingAction) {
      return;
    }

    if (this.pendingAction === 'like') {
      this.interaction.likePost(this.card.cardId);
    }

    this.swiped.emit({ action: this.pendingAction, cardId: this.card.cardId });
    this.pendingAction = null;
    this.animation = null;
  }
  likeCard() {
    this.pendingAction = 'like';
    this.animation = 'like';
  }

  dislikeCard() {
    this.pendingAction = 'dislike';
    this.animation = 'dislike';
  }
}
