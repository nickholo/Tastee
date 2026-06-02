import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class InteractionService {
  private apiUrl: string = 'http://localhost:8080';


  constructor(private http: HttpClient){}

  likePost(id: number){
    this.http.post(`${this.apiUrl}/post/${id}/like`, "").subscribe( (response) => {
      console.log(response)
    }
    )
  }
  
}
