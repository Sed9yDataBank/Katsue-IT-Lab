package com.katsueitlab.katsueserver.contact.model.audits;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"sentAt"}, allowGetters= true)
@Data
public abstract class MessageAuditModel implements Serializable{
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "sent_at", nullable = false, updatable = false)
    @CreatedDate
    private Date sentAt;
}