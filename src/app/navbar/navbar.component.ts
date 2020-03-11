import { Component, OnInit } from '@angular/core';
import { HostListener } from '@angular/core';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  logoPath = "assets/img/bg/whiteLogo.png";
  show:boolean = false;


  constructor() { }

  ngOnInit() {
    window.addEventListener("scroll", this.scroll, true); //third parameter


  }

  @HostListener('window:scroll', ['$event'])

  onWindowScroll(e) {
    let element = document.querySelector('.navbar');
    if (window.pageYOffset > element.clientHeight) {
      element.classList.remove('navbar-inverse');
    } else {
      
      element.classList.add('navbar-inverse');

    }
  }

  scroll = () => {
    const scrollPos = document.documentElement.scrollTop;

    // change '50' according to when you want to change the image
    if (scrollPos > 93) {
      console.log('New image');
      this.logoPath = "assets/img/logo-image.png";
    }
    else {
      console.log('Old Image');
      this.logoPath = "assets/img/bg/whiteLogo.png";
    }

  };

  toggleCollapse() {
    this.show = !this.show
  }
  
 
}
