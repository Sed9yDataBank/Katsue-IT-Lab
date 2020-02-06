import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent implements OnInit {

  paragraph1 : string = "Sae Hyung JUNG was born in Korea, studied in Australia and found HRDatabank corporation in Japan. He is enthusiastic about new technology and apply it to the real world.";
  paragraph2 : string = "HRDatabank was found in Dec. 2015 and successfully fundraising from one of the biggest IT company in Japan, CyberAgent.In Sept. 2016, IT lab in Tunisia was established with another successful fundraising from WILL GROUP and joined WILL GROUP in Oct.2017";
  paragraph3 : string = "It was a decision of the founder of HRDatabank, Sae Hyung.After he works with Tunisian engineers.he believes in potential of engineers in Tunisia that they can change the world.";
  paragraph4 : string = "Sillicon Valley was a small, ordinary and peaceful city with good climate in USA. But it is now the center of Technology in the world.Everything was started from just one small technology company in the garage, HP.Why not Monastir? a small, ordinary and peaceful city with good climate in Tunisia.";
  constructor() { }

  ngOnInit() {
  }

}
