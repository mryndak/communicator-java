package com.communicator.service.repository;

import com.communicator.domain.Attachments;
import com.communicator.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}