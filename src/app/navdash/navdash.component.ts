import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../login/auth.service';


@Component({
  selector: 'app-navdash',
  templateUrl: './navdash.component.html',
  styleUrls: ['./navdash.component.css']
})
export class NavdashComponent implements OnInit {

  isLoggedIn = false;
  constructor(private route: ActivatedRoute,private router: Router,private authenticationService: AuthService) { }

  ngOnInit() {
    this.isLoggedIn = this.authenticationService.isUserLoggedIn();
    console.log('You Are Out' + this.isLoggedIn);
  }

  handleLogout() {
    this.authenticationService.logout();
  }

}
