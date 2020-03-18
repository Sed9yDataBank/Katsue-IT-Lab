import { Component, OnInit } from '@angular/core';
import { NgbCarouselConfig } from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'app-team',
  templateUrl: './team.component.html',
  styleUrls: ['./team.component.css'],
  providers: [NgbCarouselConfig]  // add NgbCarouselConfig to the component providers
})
export class TeamComponent implements OnInit {

  images = ["joinslider1.jpg", "joinslider2.jpg"].map((n) => `assets/img/${n}`);

  constructor(config: NgbCarouselConfig) { 
    config.interval = 6000;
    config.wrap = true;
    config.keyboard = false;
    config.pauseOnHover = false;
  }

  ngOnInit() {
  }

}
