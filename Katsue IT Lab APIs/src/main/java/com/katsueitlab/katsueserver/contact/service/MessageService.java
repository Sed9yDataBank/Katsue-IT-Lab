package com.katsueitlab.katsueserver.contact.service;

import com.katsueitlab.katsueserver.contact.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface MessageService {

    Page<Message> getAllMessages(Pageable pageable);

    Message createMessage(Message message);

    ResponseEntity<?> deleteMessage(Long id);
}


