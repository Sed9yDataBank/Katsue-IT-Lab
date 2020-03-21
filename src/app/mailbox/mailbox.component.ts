import { Component, OnInit } from '@angular/core';
import { MailService } from './mailservice.service';
import { Mailbox } from './mailbox';


@Component({
  selector: 'app-mailbox',
  templateUrl: './mailbox.component.html',
  styleUrls: ['./mailbox.component.css']
})
export class MailboxComponent implements OnInit {

  mailBox : Mailbox [];
  
  constructor(private service: MailService) { }

  ngOnInit() {
    this.getAllMessages();
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
  
}
