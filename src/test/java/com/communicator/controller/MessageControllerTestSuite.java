package com.communicator.controller;

import com.communicator.domain.GroupMessageDto;
import com.communicator.domain.MessageDto;
import com.communicator.domain.UserConvDto;
import com.communicator.facade.MessageFacade;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MessageController.class)
public class MessageControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MessageFacade facade;

    @Test
    public void shouldCreateMessage() throws Exception {
        //Given
        UserConvDto userConvDto = UserConvDto.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .build();
        List<UserConvDto> users = new ArrayList<>();
        users.add(userConvDto);
        GroupMessageDto groupMessageDto = GroupMessageDto.builder()
                .id(1L)
                .usersInConv(users)
                .build();
        MessageDto messageDto = MessageDto.builder()
                .id(1L)
                .author(userConvDto)
                .groupMessage(groupMessageDto)
                .content("Hello")
                .read(false)
                .build();
        when(facade.createMessage(messageDto)).thenReturn(messageDto);
        //When & Then
        mockMvc.perform(post("/v1/conv/msg")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(messageDto)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.author.firstname", is("Jan")))
                .andExpect(jsonPath("$.author.lastname", is("Szewczyk")))
                .andExpect(jsonPath("$.content", is("Hello")))
                .andExpect(jsonPath("$.groupMessage.usersInConv", hasSize(1)));

    }

    @Test
    public void shouldUpdateUser() throws Exception {
        //Given
        UserConvDto userConvDto = UserConvDto.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .build();
        List<UserConvDto> users = new ArrayList<>();
        users.add(userConvDto);
        GroupMessageDto groupMessageDto = GroupMessageDto.builder()
                .id(1L)
                .usersInConv(users)
                .build();
        MessageDto messageDto = MessageDto.builder()
                .id(1L)
                .author(userConvDto)
                .groupMessage(groupMessageDto)
                .content("Hello")
                .read(false)
                .build();
        when(facade.updateMessage(1L)).thenReturn(messageDto);
        //When & Then
        mockMvc.perform(put("/v1/conv/msg/read/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.author.firstname", is("Jan")))
                .andExpect(jsonPath("$.author.lastname", is("Szewczyk")))
                .andExpect(jsonPath("$.content", is("Hello")))
                .andExpect(jsonPath("$.groupMessage.usersInConv", hasSize(1)));

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
