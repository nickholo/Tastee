import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {
  IonContent,
  IonHeader,
  IonTitle,
  IonToolbar,
  IonImg,
  IonCardHeader,
  IonCard,
  IonAvatar,
  IonCardTitle,
  IonCardSubtitle,
  IonCardContent,
  IonButton,
} from '@ionic/angular/standalone';
import { ProfileService } from 'src/app/profile-service';
import { Profile } from 'src/app/profile';
import { Card } from 'src/app/Card';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.page.html',
  styleUrls: ['./profile.page.scss'],
  standalone: true,
  imports: [
    IonButton,
    IonCardContent,
    IonCardSubtitle,
    IonCardTitle,
    IonAvatar,
    IonCard,
    IonCardHeader,
    IonImg,
    IonContent,
    IonHeader,
    IonTitle,
    IonToolbar,
    CommonModule,
    FormsModule,
  ],
})
export class ProfilePage implements OnInit {
  profile?: Profile;
  recentPosts: Card[] = [];
  readonly profileFallbackImageUrl =
    "data:image/svg+xml;charset=UTF-8,%3Csvg xmlns='http://www.w3.org/2000/svg' width='256' height='256' viewBox='0 0 256 256'%3E%3Cdefs%3E%3ClinearGradient id='g' x1='0%25' y1='0%25' x2='100%25' y2='100%25'%3E%3Cstop offset='0%25' stop-color='%23f8e9d8'/%3E%3Cstop offset='100%25' stop-color='%23d9b28c'/%3E%3C/linearGradient%3E%3C/defs%3E%3Crect width='256' height='256' rx='128' fill='url(%23g)'/%3E%3Ccircle cx='128' cy='103' r='46' fill='%23fff8ef' fill-opacity='0.9'/%3E%3Cpath d='M52 216c14-36 44-54 76-54s62 18 76 54' fill='%23fff8ef' fill-opacity='0.9'/%3E%3C/svg%3E";

  constructor(private profileService: ProfileService) {}

  ngOnInit() {
    this.profileService.getProfile().subscribe({
      next: (data) => {
        this.profile = data;
        this.profileService.getPostsForProfile(data.username).subscribe({
          next: (posts) => {
            this.recentPosts = posts;
            console.table(this.recentPosts)
          },
          error: (error) => {
            console.log('Error loading profile posts: ', error);
          },
        });
      },
      error: (error) => {
        console.log('Error Initializing Profile: ', error);
      },
    });
  }

  getProfileImageUrl(): string {
    const profileImage = this.profile?.profilePictureUrl?.trim();
    return profileImage ? profileImage : this.profileFallbackImageUrl;
  }
}
