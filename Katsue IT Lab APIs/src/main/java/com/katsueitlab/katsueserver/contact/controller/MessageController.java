package com.katsueitlab.katsueserver.contact.controller;

import com.katsueitlab.katsueserver.contact.model.Message;
import com.katsueitlab.katsueserver.contact.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/messages")
    public Page<Message> getAllMessages(Pageable pageable) {
        return messageService.getAllMessages(pageable);
    }

    @PostMapping("/messages")
    public Message createMessage(@Valid @RequestBody Message message) {
        return messageService.createMessage(message);
    }

    @DeleteMapping("/messages/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable Long id) {
        return messageService.deleteMessage(id);
    }
}


