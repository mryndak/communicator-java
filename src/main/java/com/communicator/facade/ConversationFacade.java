package com.communicator.facade;

import com.communicator.domain.Conversation;
import com.communicator.domain.ConversationDto;
import com.communicator.service.ConversationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ConversationFacade {
    private final ConversationService service;

    public ConversationDto createConversation(ConversationDto conversationDto){
        return service.create(conversationDto);
    }

    public ConversationDto searchForConversation(Long authorId, Long receiverId){
        return service.getByTwoId(authorId, receiverId);
    }

    public List<ConversationDto> searchForAllConversations(Long authorId){
        return service.getByUserId(authorId);
    }

    public ConversationDto fetchConversation(Long id) {
        return service.getById(id);
    }
}
