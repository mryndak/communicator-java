package com.communicator.service;

import com.communicator.domain.GroupMessage;
import com.communicator.domain.GroupMessageDto;
import com.communicator.domain.Notification;
import com.communicator.domain.NotificationDto;
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
    private final NotificationService service;
    private final GroupMessageMapper mapper;

    public List<GroupMessageDto> getAll(){
        return mapper.mapToGroupMessageDtoList(repository.findAll());
    }

    public GroupMessageDto getById(Long id) {
        try{
            GroupMessage groupMessage = repository.findById(id).orElseThrow(GroupMessageNotFoundException::new);
            return mapper.mapToGroupMessageDto(groupMessage);
        }catch (GroupMessageNotFoundException e){
            log.error(e.getMessage());
        }
        return GroupMessageDto.builder().build();
    }

    public GroupMessageDto create(GroupMessageDto groupMessageDto){
        GroupMessage mappedConversation = mapper.mapToGroupMessage(groupMessageDto);
        GroupMessage savedConversation = repository.save(mappedConversation);
        List<String> params = new ArrayList<>();
        params.add(String.valueOf(savedConversation.getId()));
        NotificationDto notificationDto = NotificationDto.builder()
                .receiver(groupMessageDto.getUsersInConv().get(0))
                .typeOfOperation("newConversation")
                .operationsParameters(params)
                .build();
        service.create(notificationDto);
        notificationDto.setReceiver(groupMessageDto.getUsersInConv().get(1));
        service.create(notificationDto);
        return mapper.mapToGroupMessageDto(savedConversation);
    }

    public List<Long> getByUserId(Long authorId) {
        return repository.findAllConversations(authorId);
    }

    public Integer getUnReadMessages(Long convId, Long userId) {
        GroupMessage fetchedConversation = GroupMessage.builder().build();
        try{
            fetchedConversation = repository.findById(convId).orElseThrow(GroupMessageNotFoundException::new);
        }catch (GroupMessageNotFoundException e){
            log.info(e.getMessage());
        }
        return (int) fetchedConversation.getMessagesInConv().stream().filter(m -> m.getAuthor().getId().equals(userId) && !m.isRead()).count();
    }
}
