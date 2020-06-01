package com.katsueitlab.katsueserver.contact.service.implementations;

import com.katsueitlab.katsueserver.contact.exception.ResourceNotFound;
import com.katsueitlab.katsueserver.contact.model.Message;
import com.katsueitlab.katsueserver.contact.repository.MessageRepository;
import com.katsueitlab.katsueserver.contact.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public class MessageServiceImplementation implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override

    public Page<Message> getAllMessages(Pageable pageable) {
        return messageRepository.findAll(pageable);
    }

    @Override

    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public ResponseEntity<?> deleteMessage(Long id) {
        return messageRepository.findById(id).map(message -> {
            messageRepository.delete(message);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFound("Message Id " + id + " not found"));
    }
}
