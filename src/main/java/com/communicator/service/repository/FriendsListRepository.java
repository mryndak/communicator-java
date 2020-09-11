package com.communicator.service.repository;

import com.communicator.domain.Attachments;
import com.communicator.domain.FriendsList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendsListRepository extends JpaRepository<FriendsList, Long> {
}