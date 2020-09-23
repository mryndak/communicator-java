package com.communicator.service.repository;

import com.communicator.domain.Attachments;
import com.communicator.domain.Conversation;
import com.communicator.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    @Query(value = "select * from _conversation where author_user_id = ?1 and receiver_user_id = ?2",
            nativeQuery = true)
    Conversation findConversationBetween(Long a, Long b);
    @Query(value = "select * from _conversation where author_user_id = ?1 or receiver_user_id = ?1",
            nativeQuery = true)
    List<Conversation> findAllConversations(Long a);
}