package com.katsueitlab.katsueserver.contact.model;

import com.katsueitlab.katsueserver.models.audits.MessageAuditModel;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="MailBox")
@Data
public class Message extends MessageAuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    private String senderFullName;

    @NotNull
    @Size(max = 250)
    private String senderEmail;

    @NotNull
    @Size(max = 650)
    private String senderMessage;
}
