package com.communicator.facade;

import com.communicator.domain.*;
import com.communicator.service.GroupMessageService;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class GroupMessageFacadeTestSuite {

    @Mock
    private GroupMessageService service;
    @InjectMocks
    private GroupMessageFacade facade;

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
        when(service.getById(groupMessage.getId())).thenReturn(groupMessageDto);
        //When
        GroupMessageDto fetchedGroupMessageDto = facade.fetchConversation(groupMessage.getId());
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
        when(service.create(groupMessageDto)).thenReturn(groupMessageDto);
        //When
        GroupMessageDto fetchedGroupMessageDto = facade.createConversation(groupMessageDto);
        //Then
        assertEquals(fetchedGroupMessageDto.getId(), groupMessage.getId());
    }

    @Test
    public void getByUserId() {
        //Given
        List<User> users =new ArrayList<>();
        List<UserConvDto> usersDto =new ArrayList<>();
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
        usersDto.add(userDto);
        usersDto.add(userDto);
        GroupMessage groupMessage = GroupMessage.builder()
                .usersInConv(users)
                .id(1L)
                .build();
        GroupMessageDto groupMessageDto = GroupMessageDto.builder()
                .usersInConv(usersDto)
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
        given(service.getById(groupMessage.getId())).willReturn(groupMessageDto);
        //When
        GroupMessageDto allConversations = facade.fetchConversation(groupMessage.getId());
        //Then
        assertEquals(groupMessageDto.getId(), allConversations.getId());
    }

    @Test
    public void getUnReadMessages() {
        //Given
        User user = User.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .email("szewczykjan2@gmail.com")
                .build();
        List<Long> convIds = Collections.singletonList(1L);
        given(service.getByUserId(user.getId())).willReturn(convIds);
        //When
        List<Long> allConversations = facade.searchForAllConversations(user.getId());
        //Then
        assertEquals(allConversations.size(), convIds.size());
        assertEquals(allConversations.get(0), convIds.get(0));
    }

    @Test
    public void testGetUnReadMessages() {
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
        given(service.getUnReadMessages(groupMessage.getId(), user.getId())).willReturn(1);
        //When
        Integer allConversations = facade.getUnReadMessages(groupMessage.getId(), user.getId());
        //Then
        assertEquals(1, allConversations.intValue());
    }
}