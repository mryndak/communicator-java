package com.communicator.service;

import com.communicator.domain.GroupMessage;
import com.communicator.domain.GroupMessageDto;
import com.communicator.exception.GroupMessageDontExistsException;
import com.communicator.exception.GroupMessageNotFoundException;
import com.communicator.mapper.GroupMessageMapper;
import com.communicator.service.repository.GroupMessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GroupMessageService {
    private final GroupMessageRepository repository;
    private final GroupMessageMapper mapper;

    public List<GroupMessageDto> getAll(){
        return mapper.mapToGroupMessageDtoList(repository.findAll());
    }

    public GroupMessageDto getById(Long id) {
        return mapper.mapToGroupMessageDto(repository.findById(id).orElseThrow(GroupMessageNotFoundException::new));
    }

    public GroupMessageDto create(GroupMessageDto groupMessageDto){
        GroupMessage mappedConversation = mapper.mapToGroupMessage(groupMessageDto);
        GroupMessage savedConversation = repository.save(mappedConversation);
        return mapper.mapToGroupMessageDto(savedConversation);
    }

    public GroupMessageDto update(GroupMessageDto groupMessageDto){
        GroupMessage mappedConversation = mapper.mapToGroupMessage(groupMessageDto);
        GroupMessage savedConversation = repository.save(mappedConversation);
        return mapper.mapToGroupMessageDto(savedConversation);
    }

    public void delete(GroupMessageDto groupMessageDto){
        GroupMessage mappedConversation = mapper.mapToGroupMessage(groupMessageDto);
        repository.delete(mappedConversation);
    }

    private void isAttachmentExisting(Long userId) {
        if(!repository.existsById(userId)){
            throw new GroupMessageDontExistsException();
        }
    }

    public List<Long> getByUserId(Long authorId) {
        return repository.findAllConversations(authorId);
    }

    public Integer getUnReadMessages(Long convId, Long userId) {
        GroupMessage fetchedConversation = repository.findById(convId).orElseThrow(GroupMessageNotFoundException::new);
        return (int) fetchedConversation.getMessagesInConv().stream().filter(m -> m.getAuthor().getId().equals(userId) && !m.isRead()).count();
    }
}
