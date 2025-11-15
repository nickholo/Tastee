import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Card } from './Card';

// @Injectable allows the service to be injected into components
@Injectable({
  providedIn: 'root'
})
export class Feed {
  constructor(private http: HttpClient) { }
  getFeed() {
    return this.http.get<Card>('./assets/data/recipe.json');
  }
}
