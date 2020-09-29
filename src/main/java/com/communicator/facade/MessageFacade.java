package com.communicator.facade;

import com.communicator.domain.MessageDto;
import com.communicator.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageFacade {
    private final MessageService service;

    public MessageDto createMessage(MessageDto messageDto){
        return service.create(messageDto);
    }

    public MessageDto updateMessage(Long id){
        return service.changeToRead(id);
    }

    public HashMap<Long, Boolean> getAllMessagesInConv(Long userId){
        return service.getAllMessagesInConv(userId);
    }

}
