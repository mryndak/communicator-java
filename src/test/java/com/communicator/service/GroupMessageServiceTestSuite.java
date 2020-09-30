package com.communicator.service;

import com.communicator.domain.*;
import com.communicator.mapper.GroupMessageMapper;
import com.communicator.mapper.NotificationMapper;
import com.communicator.service.repository.GroupMessageRepository;
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
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class GroupMessageServiceTestSuite {

    @Mock
    private GroupMessageRepository repository;
    @InjectMocks
    private GroupMessageService service;
    @Mock
    private GroupMessageMapper mapper;
    @Mock
    private NotificationService nService;
    @Mock
    private NotificationMapper nMapper;
    @Mock
    private NotificationRepository nRepository;

    @Test
    public void getAll() {
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
        List<GroupMessage> groupMessages = new ArrayList<>();
        List<GroupMessageDto> groupMessageDtos = new ArrayList<>();
        GroupMessage groupMessage = GroupMessage.builder()
                .usersInConv(users)
                .id(1L)
                .build();
        groupMessages.add(groupMessage);
        GroupMessageDto groupMessageDto = GroupMessageDto.builder()
                .usersInConv(userConvDtos)
                .id(1L)
                .build();
        List<MessageBlopDto> messageBlopDtos = new ArrayList<>();
        Message message = Message.builder()
                .id(1L)
                .author(user)
                .content("hej")
                .groupMessage(groupMessage)
                .read(false)
                .build();
        MessageBlopDto messageBlopDto = MessageBlopDto.builder()
                .id(1L)
                .author(userDto)
                .content("hej")
                .attachmentsList(new ArrayList<>())
                .read(false)
                .build();
        messageBlopDtos.add(messageBlopDto);
        groupMessageDto.setMessagesInConv(messageBlopDtos);
        groupMessageDtos.add(groupMessageDto);
        when(mapper.mapToGroupMessageDtoList(groupMessages)).thenReturn(groupMessageDtos);
        when(repository.findAll()).thenReturn(groupMessages);
        //When
        List<GroupMessageDto> fetchedGroupMessageDto = service.getAll();
        //Then
        assertEquals(fetchedGroupMessageDto.size(), groupMessages.size());
        assertEquals(fetchedGroupMessageDto.get(0).getMessagesInConv().get(0).getId(), messageBlopDtos.get(0).getId());
        assertEquals(fetchedGroupMessageDto.get(0).getMessagesInConv().get(0).getAuthor(), messageBlopDtos.get(0).getAuthor());
        assertEquals(fetchedGroupMessageDto.get(0).getMessagesInConv().get(0).getAttachmentsList(), messageBlopDtos.get(0).getAttachmentsList());
        assertEquals(fetchedGroupMessageDto.get(0).getMessagesInConv().get(0).getContent(), messageBlopDtos.get(0).getContent());
        assertEquals(fetchedGroupMessageDto.get(0).getMessagesInConv().get(0).isRead(), messageBlopDtos.get(0).isRead());
    }

    @Test
    public void getById() {
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
        when(mapper.mapToGroupMessageDto(groupMessage)).thenReturn(groupMessageDto);
        given(repository.findById(groupMessage.getId())).willReturn(Optional.of(groupMessage));
        //When
        GroupMessageDto fetchedGroupMessageDto = service.getById(1L);
        //Then
        assertEquals(fetchedGroupMessageDto.getId(), groupMessage.getId());
    }

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
        Notification notification = Notification.builder()
                .id(1L)
                .receiver(user)
                .typeOfOperation("newConversation")
                .operationsParameters(Collections.singletonList("2"))
                .build();
        NotificationDto notificationDto = NotificationDto.builder()
                .id(1L)
                .receiver(userDto)
                .typeOfOperation("newConversation")
                .operationsParameters(Collections.singletonList("2"))
                .build();
        when(repository.save(groupMessage)).thenReturn(groupMessage);
        when(nMapper.mapToNotification(notificationDto)).thenReturn(notification);
        when(nMapper.mapToNotificationDto(notification)).thenReturn(notificationDto);
        when(mapper.mapToGroupMessageDto(groupMessage)).thenReturn(groupMessageDto);
        when(mapper.mapToGroupMessage(groupMessageDto)).thenReturn(groupMessage);
        when(nService.create(notificationDto)).thenReturn(notificationDto);
        given(nRepository.save(notification)).willReturn(notification);
        //When
        GroupMessageDto fetchedGroupMessageDto = service.create(groupMessageDto);
        //Then
        assertEquals(fetchedGroupMessageDto.getId(), groupMessage.getId());
    }

    @Test
    public void getByUserId() {
        //Given
        User user = User.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .email("szewczykjan2@gmail.com")
                .build();
        List<Long> convIds = Collections.singletonList(1L);
        given(repository.findAllConversations(user.getId())).willReturn(convIds);
        //When
        List<Long> allConversations = service.getByUserId(user.getId());
        //Then
        assertEquals(allConversations.size(), convIds.size());
        assertEquals(allConversations.get(0), convIds.get(0));
    }

    @Test
    public void getUnReadMessages() {
        //Given
        List<User> users =new ArrayList<>();
        User user = User.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .email("szewczykjan2@gmail.com")
                .build();
        users.add(user);
        users.add(user);
        GroupMessage groupMessage = GroupMessage.builder()
                .usersInConv(users)
                .id(1L)
                .build();
        Message message = Message.builder()
                .id(1L)
                .author(user)
                .content("hej")
                .groupMessage(groupMessage)
                .read(false)
                .build();
        groupMessage.setMessagesInConv(Collections.singletonList(message));
        groupMessage.setUsersInConv(users);
        given(repository.findById(groupMessage.getId())).willReturn(Optional.of(groupMessage));
        //When
        Integer allConversations = service.getUnReadMessages(groupMessage.getId(), user.getId());
        //Then
        assertEquals(1, allConversations.intValue());
    }
}