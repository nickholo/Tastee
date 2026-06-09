import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Card } from './Card';
import { map, tap } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class FeedService {
  apiUrl: string = environment.apiUrl;
  constructor(private http: HttpClient) {}

  getFeed() {
    return this.http.get<Card[]>(`${this.apiUrl}/post/random`).pipe(
      // RXJS Transform operator
      map(data => data.map(item => Card.fromJson(item))),
      tap(data => console.log("Casted Feed: " +data))
  )}

  requestPost() {
    return this.http.get<Card[]>(`${this.apiUrl}/post/random`).pipe(
      map(data => Card.fromJson(data)),
      tap(data => console.log("Casted Post" +data.cardId))

    );
  }
}