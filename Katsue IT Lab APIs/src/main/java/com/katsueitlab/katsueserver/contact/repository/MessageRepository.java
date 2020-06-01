package com.katsueitlab.katsueserver.contact.repository;

import com.katsueitlab.katsueserver.contact.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}

