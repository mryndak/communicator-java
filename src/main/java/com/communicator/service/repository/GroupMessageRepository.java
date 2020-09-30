package com.communicator.service.repository;

import com.communicator.domain.GroupMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings("SqlResolve")
@Repository
public interface GroupMessageRepository extends JpaRepository<GroupMessage, Long> {
    @Query(value = "select _group_message_users_in_conv.conversations_id from _group_message_users_in_conv where _group_message_users_in_conv.users_in_conv_id = ?1", nativeQuery = true)
    List<Long> findAllConversations(Long a);
}