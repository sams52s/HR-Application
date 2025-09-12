import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms'; 
import { NgIf } from '@angular/common';        
import { HttpClient } from '@angular/common/http';
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
  imports: [FormsModule, NgIf],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  employeeId = '';
  password = '';
  errorMessage = '';

  constructor(private http: HttpClient, private router: Router) {}

  login() {
    this.errorMessage = '';
    if (!this.employeeId) {
      this.errorMessage = 'Employee ID is required';
      return;
    }
    if (!this.password) {
      this.errorMessage = 'Password is required';
      return;
    }
    this.http.post<LoginResponse>('/api/auth/login', {
      employeeId: this.employeeId,
      password: this.password
    }).subscribe({
      next: (res) => {
        // Save logged-in employee info
        localStorage.setItem('employee', JSON.stringify(res));
        // Redirect to employees list
        this.router.navigate(['/employees']);
      },
      error: () => {
        this.errorMessage = 'Invalid Employee ID or Password';
      }
    });
  }
}