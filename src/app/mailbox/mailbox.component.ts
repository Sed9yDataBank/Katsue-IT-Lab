import { Component, OnInit } from '@angular/core';
import { MailService } from './mailservice.service';
import { Mailbox, Email } from './mailbox';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';


@Component({
  selector: 'app-mailbox',
  templateUrl: './mailbox.component.html',
  styleUrls: ['./mailbox.component.css']
})
export class MailboxComponent implements OnInit {

  mailBox : Mailbox [];
  showModal: boolean;
  replyForm: FormGroup;
  sent: boolean = false;
  replyObject:Email;

  constructor(private service: MailService, private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.getAllMessages();
    this.replyForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email, Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')]],        
      subject: ['', [Validators.required, Validators.minLength(6)]],
      message: ['', [Validators.required, Validators.minLength(5)]],
    });
  }

  getAllMessages() {
    this.service.getMessages().subscribe(data => {
      this.mailBox = data;
    },
    error => {
      console.log('404 Not Working', error)}
    );
  }

	deleteMessageById(id: string) {
		this.service.deleteMessage(id).subscribe(res => {
				this.getAllMessages();
      },
      error => {
        console.log('404 It Will Not Delete', error)}
      );
	}
  
  show()
  {
    this.showModal = true; // Show-Hide Modal Check
    
  }
  //Bootstrap Modal Close event
  hide()
  {
    this.showModal = false;
  }
  clearForm(){
    (<HTMLFormElement>document.getElementById("connection")).reset();
  }
  get f() { return this.replyForm.controls; }
  onSent() {
    this.sent = true;
    // stop here if form is invalid
    if (this.replyForm.invalid) {
        return;
    }
    if(this.sent)
    {
      this.showModal = false;
      this.replyForm.reset();
    }
  }

  sendReply(form:FormGroup){

    console.log("Your Reply Mail Has Been Sent Sent ",this.replyForm);
    this.replyObject=new Email()
    this.replyObject.to=this.replyForm.value["email"];
    this.replyObject.subject=this.replyForm.value["subject"];
    this.replyObject.message=this.replyForm.value["message"];
  
    console.log("Email ", this.replyObject)
    this.service.sendReplyMail(this.replyObject).subscribe(data=>{
  
    })
   }
}
