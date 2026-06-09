import { Component, OnInit } from '@angular/core';
import { IonHeader, IonToolbar, IonTitle, IonContent } from '@ionic/angular/standalone';
import { RecipeCardComponent } from "./recipe-card/recipe-card.component";
import { Card } from '../../Card';
import { FeedService } from '../../feed-service';

@Component({
  selector: 'app-tab1',
  templateUrl: 'tab1.page.html',
  styleUrls: ['tab1.page.scss'],
  imports: [IonHeader, IonToolbar, IonTitle, IonContent, RecipeCardComponent],
})
export class Tab1Page {
  constructor(private feed: FeedService) {}

  content: Card[] = []

  // Initialize Feed with 2 posts
  ngOnInit() {
  this.feed.getFeed().subscribe({
    next: (data) => {
      this.content.push(...data);
      console.log('Feed data received:', this.content);
    },
    error: (err) => console.error('Feed error:', err)
  });
  }
  
  updateFeed(event: { action: 'like' | 'dislike', cardId: number }){
    // Remove emitted card
    console.log('Card removed, remaining content:', this.content)
    this.content = this.content.filter((card) => card.cardId !== event.cardId)
    // console.log(this.content[0].cardId)

    // Request new card
    this.feed.getFeed().subscribe({
    next: (data) => {
      this.content.push(...data);
      console.log('Feed data received:', this.content);
    },
    error: (err) => console.error('Feed error:', err)
  });

  }
}