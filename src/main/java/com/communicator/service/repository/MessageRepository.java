package com.communicator.service.repository;

import com.communicator.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query(value = "insert into _group_message_messages_in_conv(group_message_id, messages_in_conv_id) VALUES (?1, ?2)", nativeQuery = true)
    void createMessageInConv(Long a, Long b);
}