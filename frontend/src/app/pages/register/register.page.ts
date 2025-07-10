import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonContent, IonHeader, IonTitle, IonToolbar, IonButton } from '@ionic/angular/standalone';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.page.html',
  styleUrls: ['./register.page.scss'],
  standalone: true,
  imports: [IonContent, IonHeader, IonTitle, IonToolbar, IonButton, CommonModule, FormsModule]
})
export class RegisterPage {

  email = '';
  password = '';
  constructor(private auth: AuthService, private router: Router) {
    if (auth.isAuthenticated()) router.navigate(['/home']);
  }

  register() {
    this.auth.register(this.email, this.password).subscribe(() =>
      this.router.navigate(['/home'])
    );
  }

}
