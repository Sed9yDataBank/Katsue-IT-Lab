import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent implements OnInit {

  paragraph1 : string = "Dann Lenard was born in Netherlands, studied in Norway and found Katsue corporation in his home town Amsterdam with one of his colleagues Nethan Bill. He is enthusiastic about new technology and implementing it in our daily functions in life and apply it to the real world.";
  paragraph2 : string = "Katsue was found in Dec. 2012 and successfully fundraising from the Dutch government for winning the award of the best startup idea In Sept 2014, IT lab in Amsterdam was established with another successfully and got faundraised again by the government to buy equipmenets to build robots for classified reasons";
  paragraph3 : string = "It was a decision of the founders of Katsue, Dann Lenard And Nethan Bill. After they worked with Dutch engineers. they believes in potential of engineers in their home country that they can change the world.";
  paragraph4 : string = "Sillicon Valley was a small, ordinary and peaceful city with good climate in USA. But it is now the center of Technology in the world.Everything was started from just one small technology company in the garage, HP.Why not Amsterdam? a Big, city surrounded by other tech lead companies, with its good climate in Netherlands.";
  constructor() { }

  ngOnInit() {
  }

}
