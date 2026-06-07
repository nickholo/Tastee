import { Component, inject, OnInit } from '@angular/core';
import { CommonModule, formatCurrency } from '@angular/common';
import { FormGroup, FormsModule } from '@angular/forms';
import { IonContent, IonHeader, IonTitle, IonToolbar, IonCard, IonCardHeader, IonCardContent, IonLabel, IonInput, IonButton, IonInputPasswordToggle } from '@ionic/angular/standalone';
import { IonItem } from '@ionic/angular/standalone';
import { IonCardTitle } from '@ionic/angular/standalone';
import { AuthService } from 'src/app/auth-service';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
  standalone: true,
  imports: [IonContent, IonHeader, IonTitle, IonToolbar, CommonModule, FormsModule, IonCard, IonCardHeader, IonItem, IonCardTitle, IonCardContent, IonLabel, IonInput, IonButton, ReactiveFormsModule, IonInputPasswordToggle]
})
export class LoginPage implements OnInit {
  
  router = inject(Router)
  // Initial Value is an empty string
  loginForm = new FormGroup({
    username : new FormControl(''),
    password : new FormControl('')  
  })

  constructor(private authService: AuthService) { }

  ngOnInit() {
  }
  login(username: any, password: any){
    console.log("Attempting to login (Inside LoginPage)")
    this.authService.login(username, password).subscribe(
      {
    next: () => this.router.navigate(['/app/tabs/tab1']),
    error: (err) => console.error('Login failed', err)
  }
    )
  }


}
