import { Component, OnInit } from '@angular/core';
import { NgbCarouselConfig } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import { StaffService } from '../staffphotos/staff.service';
import { StaffUploads } from '../staffphotos/staff-uploads';


@Component({
  selector: 'app-team',
  templateUrl: './team.component.html',
  styleUrls: ['./team.component.css'],
  providers: [NgbCarouselConfig]  // add NgbCarouselConfig to the component providers
})
export class TeamComponent implements OnInit {

  images = ["joinslider1.jpg", "joinslider2.jpg"].map((n) => `assets/img/${n}`);
  allStaffUploads : StaffUploads[];
  statusCode: number;

  constructor(config: NgbCarouselConfig, private router: Router, private baseservice: StaffService) { 
    config.interval = 6000;
    config.wrap = true;
    config.keyboard = false;
    config.pauseOnHover = false;
  }

  ngOnInit() {
  }

  scrollToBreweries() {
    this.router.onSameUrlNavigation = "reload";
    this.router.navigate(["/joinus"], { fragment: "breweries" }).finally(() => {
        this.router.onSameUrlNavigation = "ignore"; // Restore config after navigation completes
    });
  }

  getAllStaff() {
		this.baseservice.getUploads().subscribe(
				data => this.allStaffUploads = data,
        errorCode => this.statusCode = errorCode
      );
  }
}
