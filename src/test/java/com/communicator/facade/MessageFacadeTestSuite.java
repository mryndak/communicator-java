package com.communicator.facade;

import com.communicator.domain.*;
import com.communicator.service.MessageService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class MessageFacadeTestSuite {

    @Mock
    private MessageService service;
    @InjectMocks
    private MessageFacade facade;

    @Test
    public void create() {
        //Given
        List<UserConvDto> userConvDtos =new ArrayList<>();
        UserConvDto userDto = UserConvDto.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .build();
        userConvDtos.add(userDto);
        userConvDtos.add(userDto);
        GroupMessageDto groupMessageDto = GroupMessageDto.builder()
                .usersInConv(userConvDtos)
                .id(1L)
                .build();
        MessageDto messageDto = MessageDto.builder()
                .id(1L)
                .author(userDto)
                .content("hej")
                .groupMessage(groupMessageDto)
                .read(false)
                .build();
        given(service.create(messageDto)).willReturn(messageDto);
        //When
        MessageDto fetchedNotificationDto = facade.createMessage(messageDto);
        //Given
        assertEquals(fetchedNotificationDto.getId(), messageDto.getId());
    }

    @Test
    public void changeToRead() {
        //Given
        List<User> users =new ArrayList<>();
        List<UserConvDto> userConvDtos =new ArrayList<>();
        User user = User.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .email("szewczykjan2@gmail.com")
                .build();
        users.add(user);
        users.add(user);
        UserConvDto userDto = UserConvDto.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .build();
        userConvDtos.add(userDto);
        userConvDtos.add(userDto);
        GroupMessage groupMessage = GroupMessage.builder()
                .usersInConv(users)
                .id(1L)
                .build();
        GroupMessageDto groupMessageDto = GroupMessageDto.builder()
                .usersInConv(userConvDtos)
                .id(1L)
                .build();
        Message message = Message.builder()
                .id(1L)
                .author(user)
                .content("hej")
                .groupMessage(groupMessage)
                .read(false)
                .build();
        MessageDto messageDto = MessageDto.builder()
                .id(1L)
                .author(userDto)
                .content("hej")
                .groupMessage(groupMessageDto)
                .read(false)
                .build();
        given(service.changeToRead(message.getId())).willReturn(messageDto);
        //When
        MessageDto fetchMessage = facade.updateMessage(message.getId());
        //Given
        assertEquals(fetchMessage.getId(), message.getId());
        assertEquals(fetchMessage.getContent(), message.getContent());
        assertEquals(fetchMessage.getAuthor().getId(),message.getAuthor().getId());
        assertEquals(fetchMessage.getAuthor().getFirstname(),message.getAuthor().getFirstname());
        assertEquals(fetchMessage.getAuthor().getLastname(),message.getAuthor().getLastname());
    }
}