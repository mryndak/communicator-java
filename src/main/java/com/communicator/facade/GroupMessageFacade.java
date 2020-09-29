package com.communicator.facade;

import com.communicator.domain.GroupMessageDto;
import com.communicator.service.GroupMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class GroupMessageFacade {
    private final GroupMessageService service;

    public GroupMessageDto createConversation(GroupMessageDto conversationDto){
        return service.create(conversationDto);
    }

    public List<Long> searchForAllConversations(Long authorId){
        return service.getByUserId(authorId);
    }

    public GroupMessageDto fetchConversation(Long id) {
        return service.getById(id);
    }

    public Integer getUnReadMessages(Long convId, Long userId) {
        return service.getUnReadMessages(convId, userId);
    }
}
