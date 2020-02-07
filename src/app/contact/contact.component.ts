import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {ContactService} from './contact.service';
import {ContatModel} from './contactModel'
@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {

  title = 'angularpopup';
  showModal: boolean;
  messageForm: FormGroup;
  sent = false;
  contactObject:ContatModel
  constructor(private formBuilder: FormBuilder,private service:ContactService) { }
  show()
  {
    this.showModal = true; // Show-Hide Modal Check
    
  }
  //Bootstrap Modal Close event
  hide()
  {
    this.showModal = false;
  }
  ngOnInit() {
    this.messageForm = this.formBuilder.group({
      firstname: ['', [Validators.required, Validators.minLength(6)]],
      email: ['', [Validators.required, Validators.email]],
      message: ['', [Validators.required, Validators.minLength(5)]],
    });

}
// convenience getter for easy access to form fields
get f() { return this.messageForm.controls; }
onSent() {
    this.sent = true;
    // stop here if form is invalid
    if (this.messageForm.invalid) {
        return;
    }
    if(this.sent)
    {
      this.showModal = false;
      this.messageForm.reset();
    }
   
}
clearForm(){
  (<HTMLFormElement>document.getElementById("connection")).reset();
 }
 
 sendData(form:FormGroup){

  console.log("My forrrrm",this.messageForm);
  this.contactObject=new ContatModel()
  this.contactObject.senderFirstName=this.messageForm.value["firstname"];
  this.contactObject.senderEmail=this.messageForm.value["email"];
  this.contactObject.senderMessage=this.messageForm.value["message"];

  console.log("object to send", this.contactObject)
  this.service.createMessage(this.contactObject).subscribe(data=>{

  })
 }

}
