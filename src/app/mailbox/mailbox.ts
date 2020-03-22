export class Mailbox {
    id: number;
    senderFullName: String;
    senderEmail: String;
    senderMessage: String;
    sentAt: Date;
}
export class Email {
    to: String;
    subject: String;
    message: String;
}