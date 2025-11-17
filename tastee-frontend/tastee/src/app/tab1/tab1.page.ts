import { Component, OnInit } from '@angular/core';
import { IonHeader, IonToolbar, IonTitle, IonContent } from '@ionic/angular/standalone';
import { RecipeCardComponent } from "../recipe-card/recipe-card.component";
import { Card } from '../Card';
import { Feed } from '../feed';

@Component({
  selector: 'app-tab1',
  templateUrl: 'tab1.page.html',
  styleUrls: ['tab1.page.scss'],
  imports: [IonHeader, IonToolbar, IonTitle, IonContent, RecipeCardComponent],
})
export class Tab1Page {
  constructor(private feed: Feed) {}

  content: Card[] = []
  test: string = "https://www.thespruceeats.com/thmb/lko3xX8clhOrC894t9Drb6MoiX0=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/easy-and-hearty-vegetable-soup-99538-hero-01-1d3b936ff03144af95ddca7640259c11.jpg"

  ngOnInit(){
    for (let i = 0; i<1; i++){
      this.feed.getFeed().subscribe((data) => {
        data.forEach(element => {
          this.content.push(element)
          
        });
        console.log('Feed data received:', this.content);
    })
    }
  }
  
  updateFeed(event: { action: 'like' | 'dislike', cardId: number }){
    this.content = this.content.filter((card) => card.cardId !== event.cardId)
    console.log('Card removed, remaining content:', this.content)
  }
}