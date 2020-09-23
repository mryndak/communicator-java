package com.communicator.facade;

import com.communicator.domain.MessageDto;
import com.communicator.domain.UserConvDto;
import com.communicator.domain.UserDto;
import com.communicator.domain.UserSearchDto;
import com.communicator.facade.logic.UserStatusToggle;
import com.communicator.service.MessageService;
import com.communicator.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageFacade {
    private final MessageService service;

    public MessageDto createMessage(MessageDto messageDto){
        return service.create(messageDto);
    }

}
