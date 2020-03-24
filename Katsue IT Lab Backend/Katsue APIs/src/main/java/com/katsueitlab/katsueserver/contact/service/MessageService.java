package com.katsueitlab.katsueserver.contact.service;

import com.katsueitlab.katsueserver.exceptions.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public Page<Message> getAllMessages(Pageable pageable) {
        return messageRepository.findAll(pageable);
    }

    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }

    public ResponseEntity<?> deleteMessage(Long id) {
        return messageRepository.findById(id).map(message -> {
            messageRepository.delete(message);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFound("Message Id " + id + " not found"));
    }
}


