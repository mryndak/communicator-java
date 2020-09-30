package com.communicator.controller;

import com.communicator.domain.*;
import com.communicator.facade.AttachmentsFacade;
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
@WebMvcTest(AttachmentsController.class)
public class AttachmentsControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AttachmentsFacade facade;

    @Test
    public void getAllAttachments() throws Exception {
        //Given
        List<AttachmentsDto> attachmentsDtos = new ArrayList<>();
        AttachmentsDto attachmentsDto = AttachmentsDto.builder()
                .id(1L)
                .fileName("googlePic")
                .filePath("https://google.com")
                .build();
        attachmentsDtos.add(attachmentsDto);
        when(facade.getAllAttachments()).thenReturn(attachmentsDtos);
        //When & Then
        mockMvc.perform(get("/v1/attachments")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].fileName", is("googlePic")))
                .andExpect(jsonPath("$[0].filePath", is("https://google.com")));

    }

    @Test
    public void getAttachmentById() throws Exception {
        //Given
        AttachmentsDto attachmentsDto = AttachmentsDto.builder()
                .id(1L)
                .fileName("googlePic")
                .filePath("https://google.com")
                .build();
        when(facade.getAttachmentById(attachmentsDto.getId())).thenReturn(attachmentsDto);
        //When & Then
        mockMvc.perform(get("/v1/attachments/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.fileName", is("googlePic")))
                .andExpect(jsonPath("$.filePath", is("https://google.com")));
    }

    @Test
    public void createAttachment() throws Exception {
        //Given
        AttachmentsDto attachmentsDto = AttachmentsDto.builder()
                .id(1L)
                .fileName("googlePic")
                .filePath("https://google.com")
                .build();
        when(facade.createAttachment(attachmentsDto)).thenReturn(attachmentsDto);
        //When & Then
        mockMvc.perform(post("/v1/attachments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(attachmentsDto)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.fileName", is("googlePic")))
                .andExpect(jsonPath("$.filePath", is("https://google.com")));
    }

    @Test
    public void updateAttachment() throws Exception {
        //Given
        AttachmentsDto attachmentsDto = AttachmentsDto.builder()
                .id(1L)
                .fileName("googlePic")
                .filePath("https://google.com")
                .build();
        when(facade.updateAttachment(attachmentsDto)).thenReturn(attachmentsDto);
        //When & Then
        mockMvc.perform(put("/v1/attachments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(attachmentsDto)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.fileName", is("googlePic")))
                .andExpect(jsonPath("$.filePath", is("https://google.com")));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
