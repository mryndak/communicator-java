package com.communicator.service;

import com.communicator.domain.*;
import com.communicator.mapper.MessageMapper;
import com.communicator.mapper.NotificationMapper;
import com.communicator.service.repository.MessageRepository;
import com.communicator.service.repository.NotificationRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class MessageServiceTestSuite {

    @Mock
    private MessageRepository repository;
    @InjectMocks
    private MessageService service;
    @Mock
    private MessageMapper mapper;
    @Mock
    private NotificationService nService;
    @Mock
    private NotificationMapper nMapper;
    @Mock
    private NotificationRepository nRepository;

    @Test
    public void create() {
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
        Notification notification = Notification.builder()
                .receiver(message.getAuthor())
                .typeOfOperation("newMessage")
                .operationsParameters(Collections.singletonList("2"))
                .build();
        NotificationDto notificationDto = NotificationDto.builder()
                .receiver(messageDto.getAuthor())
                .typeOfOperation("newMessage")
                .operationsParameters(Collections.singletonList("2"))
                .build();
        when(nService.create(notificationDto)).thenReturn(notificationDto);
        when(mapper.mapToMessageDto(message)).thenReturn(messageDto);
        when(mapper.mapToMessage(messageDto)).thenReturn(message);
        when(nMapper.mapToNotification(notificationDto)).thenReturn(notification);
        when(nMapper.mapToNotificationDto(notification)).thenReturn(notificationDto);
        given(repository.save(message)).willReturn(message);
        given(nRepository.save(notification)).willReturn(notification);
        //When
        MessageDto fetchedNotificationDto = service.create(messageDto);
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
        when(mapper.mapToMessageDto(message)).thenReturn(messageDto);
        given(repository.getOne(1L)).willReturn(message);
        given(repository.save(message)).willReturn(message);
        //When
        MessageDto fetchMessage = service.changeToRead(message.getId());
        //Given
        assertEquals(fetchMessage.getId(), message.getId());
        assertEquals(fetchMessage.getContent(), message.getContent());
        assertEquals(fetchMessage.getAuthor().getId(),message.getAuthor().getId());
        assertEquals(fetchMessage.getAuthor().getFirstname(),message.getAuthor().getFirstname());
        assertEquals(fetchMessage.getAuthor().getLastname(),message.getAuthor().getLastname());
    }
}