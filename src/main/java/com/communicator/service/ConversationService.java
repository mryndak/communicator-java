package com.communicator.service;

import com.communicator.domain.Conversation;
import com.communicator.domain.ConversationDto;
import com.communicator.exception.ConversationDontExistsException;
import com.communicator.exception.ConversationNotFoundException;
import com.communicator.mapper.ConversationMapper;
import com.communicator.service.repository.ConversationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConversationService {
    private final ConversationRepository repository;
    private final ConversationMapper mapper;

    public List<ConversationDto> getAll(){
        return mapper.mapToConversationDtoList(repository.findAll());
    }

    public ConversationDto getById(Long id) {
        return mapper.mapToConversationDto(repository.findById(id).orElseThrow(ConversationNotFoundException::new));
    }

    public ConversationDto getByTwoId(Long idA, Long idB){
        return mapper.mapToConversationDto(repository.findConversationBetween(idA, idB));
    }

    public ConversationDto create(ConversationDto conversationDto){
        Conversation mappedConversation = mapper.mapToConversation(conversationDto);
        Conversation savedConversation = repository.save(mappedConversation);
        return mapper.mapToConversationDto(savedConversation);
    }

    public ConversationDto update(ConversationDto conversationDto){
        Conversation mappedConversation = mapper.mapToConversation(conversationDto);
        Conversation savedConversation = repository.save(mappedConversation);
        return mapper.mapToConversationDto(savedConversation);
    }

    public void delete(ConversationDto conversationDto){
        Conversation mappedConversation = mapper.mapToConversation(conversationDto);
        repository.delete(mappedConversation);
    }

    private void isAttachmentExisting(Long userId) {
        if(!repository.existsById(userId)){
            throw new ConversationDontExistsException();
        }
    }

    public List<ConversationDto> getByUserId(Long authorId) {
        return mapper.mapToConversationDtoList(repository.findAllConversations(authorId));
    }
}
