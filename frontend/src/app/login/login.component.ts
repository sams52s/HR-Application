import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms'; 
import { NgIf } from '@angular/common';        
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';

interface LoginResponse {
  employeeId: string;
  firstName: string;
  lastName: string;
  division?: string;
  building?: string;
  title?: string;
  room?: string;
}

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, NgIf, HttpClientModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  employeeId = '';
  password = '';
  errorMessage = '';

  constructor(private http: HttpClient, private router: Router) {}

  login() {
    // Directly redirect to employees page without checking
    this.router.navigate(['/employees']);
  }
}