package com.katsueitlab.katsueserver.mail.service.implementation;

import com.katsueitlab.katsueserver.mail.model.KatsueEmail;
import com.katsueitlab.katsueserver.mail.service.EmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailServiceImplementation implements EmailService {

    final private String sendGridApi = "Your_SendGripd_API_Key";

    private Mail PersonalizeEmail(KatsueEmail email) {

        Mail mail = new Mail();

        /* From information setting */
        Email fromEmail = new Email();
        fromEmail.setName("Katsue IT Lab - Netherlands");
        fromEmail.setEmail("contact@Katsue.com");

        mail.setFrom(fromEmail);
        mail.setSubject(email.getSubject());

        /* Personalization setting */
        Personalization personalization = new Personalization();
        Email to = new Email();
        to.setName(email.getToName());
        to.setEmail(email.getToEmail());
        personalization.addTo(to);

        personalization.addHeader("X-Test", "test");
        personalization.addHeader("X-Mock", "true");

        /* Substitution value settings */
        personalization.addSubstitution("%name%", "Katsue IT Lab - Netherlands");
        personalization.addSubstitution("%from%", "contact@Katsue.com");

        mail.addPersonalization(personalization);

        /* Set template id */
        mail.setTemplateId("Your_SendGrid_Template_Id");

        /* Reply to setting What We Need*/
        Email replyTo = new Email();
        replyTo.setName("Katsue IT Lab - Netherlands");
        replyTo.setEmail("contact@Katsue.com");
        mail.setReplyTo(replyTo);

        /* Adding Content of the email */
        Content content = new Content();

        /* Adding email message/body */
        content.setType("text/plain");
        content.setValue(email.getMessage());
        mail.addContent(content);

        return mail;
    }

    @Override
    public String sendMail(KatsueEmail email) {

        SendGrid sg = new SendGrid(sendGridApi);
        sg.addRequestHeader("X-Mock", "true");

        Request request = new Request();
        Mail mail = PersonalizeEmail(email);
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            ex.printStackTrace();
            return "Failed To Send Mail! " + ex.getMessage();
        }
        return "Email Has Been Sent Successfully!!";
    }
}