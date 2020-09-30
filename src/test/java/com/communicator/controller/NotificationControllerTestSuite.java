package com.communicator.controller;

import com.communicator.domain.NotificationDto;
import com.communicator.domain.UserConvDto;
import com.communicator.facade.NotificationFacade;
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
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(NotificationController.class)
public class NotificationControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private NotificationFacade facade;

    @Test
    public void shouldCreateUser() throws Exception {
        //Given
        UserConvDto userConvDto = UserConvDto.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .build();
        NotificationDto notificationDto = NotificationDto.builder()
                .id(1L)
                .receiver(userConvDto)
                .typeOfOperation("newConversation")
                .operationsParameters(new ArrayList<>(Collections.singleton("2")))
                .build();
        when(facade.createNotification(notificationDto)).thenReturn(notificationDto);
        //When & Then
        mockMvc.perform(post("/v1/users/notification")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(notificationDto)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.typeOfOperation", is("newConversation")))
                .andExpect(jsonPath("$.operationsParameters[0]", is("2")))
                .andExpect(jsonPath("$.receiver.firstname", is("Jan")))
                .andExpect(jsonPath("$.receiver.lastname", is("Szewczyk")));

    }

    @Test
    public void shouldGetAllNotificationsForUser() throws Exception {
        //Given
        UserConvDto userConvDto = UserConvDto.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .build();
        NotificationDto notificationDto = NotificationDto.builder()
                .id(1L)
                .receiver(userConvDto)
                .typeOfOperation("newConversation")
                .operationsParameters(new ArrayList<>(Collections.singleton("2")))
                .build();
        List<NotificationDto> notificationDtoList = new ArrayList<>();
        notificationDtoList.add(notificationDto);
        notificationDto.setId(2L);
        notificationDtoList.add(notificationDto);
        when(facade.getAllNotifications(1L)).thenReturn(notificationDtoList);
        //When & Then
        mockMvc.perform(get("/v1/users/notification/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(2)));

    }

    @Test
    public void shouldDeleteNotification() throws Exception {
        //Given
        //When & Then
        mockMvc.perform(delete("/v1/users/notification/delete/1")
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
