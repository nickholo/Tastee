import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Profile } from './profile';
import { tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {
  apiUrl: string = environment.apiUrl
  
  constructor(private htttp: HttpClient){}

  getProfile(){
    console.log('getProfile Called')
    return this.htttp.get<Profile>(`${this.apiUrl}/users/1/profile`)
  }
}
