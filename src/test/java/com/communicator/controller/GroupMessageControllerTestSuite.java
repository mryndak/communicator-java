package com.communicator.controller;

import com.communicator.domain.GroupMessageDto;
import com.communicator.domain.UserConvDto;
import com.communicator.facade.GroupMessageFacade;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GroupMessageController.class)
public class GroupMessageControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GroupMessageFacade facade;

    @Test
    public void shouldCreateGroupMessage() throws Exception {
        //Given
        UserConvDto userConvDto = UserConvDto.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .build();
        List<UserConvDto> users = new ArrayList<>();
        users.add(userConvDto);
        userConvDto.setId(2L);
        users.add(userConvDto);
        GroupMessageDto groupMessageDto = GroupMessageDto.builder()
                .id(1L)
                .usersInConv(users)
                .build();
        when(facade.createConversation(groupMessageDto)).thenReturn(groupMessageDto);
        //When & Then
        mockMvc.perform(post("/v1/conv")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(groupMessageDto)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.usersInConv", hasSize(2)));

    }

    @Test
    public void shouldGetGroupMessageById() throws Exception {
        //Given
        UserConvDto userConvDto = UserConvDto.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .build();
        List<UserConvDto> users = new ArrayList<>();
        users.add(userConvDto);
        userConvDto.setId(2L);
        users.add(userConvDto);
        GroupMessageDto groupMessageDto = GroupMessageDto.builder()
                .id(1L)
                .usersInConv(users)
                .build();
        when(facade.fetchConversation(groupMessageDto.getId())).thenReturn(groupMessageDto);
        //When & Then
        mockMvc.perform(get("/v1/conv/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(groupMessageDto)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.usersInConv", hasSize(2)));

    }

    @Test
    public void shouldGetAllGroupMessage() throws Exception {
        //Given
        UserConvDto userConvDto = UserConvDto.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .build();
        List<UserConvDto> users = new ArrayList<>();
        users.add(userConvDto);
        userConvDto.setId(2L);
        users.add(userConvDto);
        GroupMessageDto groupMessageDto = GroupMessageDto.builder()
                .id(1L)
                .usersInConv(users)
                .build();
        List<Long> conversations = new ArrayList<>();
        conversations.add(groupMessageDto.getId());
        when(facade.searchForAllConversations(userConvDto.getId())).thenReturn(conversations);
        //When & Then
        mockMvc.perform(get("/v1/conv/user/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0]", is(1)));

    }

    @Test
    public void shouldCountUnreadMessages() throws Exception {
        //Given
        UserConvDto userConvDto = UserConvDto.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .build();
        List<UserConvDto> users = new ArrayList<>();
        users.add(userConvDto);
        userConvDto.setId(2L);
        users.add(userConvDto);
        GroupMessageDto groupMessageDto = GroupMessageDto.builder()
                .id(1L)
                .usersInConv(users)
                .build();
        List<Long> conversations = new ArrayList<>();
        conversations.add(groupMessageDto.getId());
        Integer size = conversations.size();
        when(facade.getUnReadMessages(groupMessageDto.getId(), userConvDto.getId())).thenReturn(size);
        System.out.println(size);
        //When & Then
        mockMvc.perform(get("/v1/conv/1/unread/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
