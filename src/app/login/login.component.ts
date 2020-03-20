import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm :FormGroup;
  username: string;
  password: string;
  errorMessage = 'Server Is Down, Will Be Back Soon...';
  successMessage: string;
  invalidLogin = false;
  loginSuccess = false;

  constructor(private formBuilder :FormBuilder, private router: Router, private authService: AuthService) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
  });
  }

  handleLogin() {
    this.authService.authenticationService(this.username, this.password).subscribe((result)=> {
      this.invalidLogin = false;
      this.loginSuccess = true;
      this.successMessage = 'Login Successful, Welcome Katsue Admin.';
      this.router.navigate(['/adminpanel']);
    }, () => {
      this.invalidLogin = true;
      this.loginSuccess = false;
    });      
  }



}

