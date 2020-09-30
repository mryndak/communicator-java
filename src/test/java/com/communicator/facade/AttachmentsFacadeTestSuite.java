package com.communicator.facade;

import com.communicator.domain.Attachments;
import com.communicator.domain.AttachmentsDto;
import com.communicator.service.AttachmentsService;
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
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class AttachmentsFacadeTestSuite {

    @Mock
    private AttachmentsService service;
    @InjectMocks
    private AttachmentsFacade facade;

    @Test
    public void getAll() {
        //Given
        List<AttachmentsDto> attachmentsDtoList = new ArrayList<>();
        AttachmentsDto attachmentsDto = AttachmentsDto.builder()
                .id(1L)
                .fileName("googlePic")
                .filePath("https://google.com")
                .build();
        attachmentsDtoList.add(attachmentsDto);
        when(service.getAll()).thenReturn(attachmentsDtoList);
        //When
        List<AttachmentsDto> attachmentsDtos = facade.getAllAttachments();
        //Then
        assertEquals(attachmentsDtoList.size(), attachmentsDtos.size());
    }

    @Test
    public void getById() {
        //Given
        Attachments attachments = Attachments.builder()
                .id(1L)
                .fileName("googlePic")
                .filePath("https://google.com")
                .build();
        AttachmentsDto attachmentsDto = AttachmentsDto.builder()
                .id(1L)
                .fileName("googlePic")
                .filePath("https://google.com")
                .build();
        given(service.getById(attachments.getId())).willReturn(attachmentsDto);
        //When
        AttachmentsDto attachmentsDtos = facade.getAttachmentById(attachments.getId());
        //Then
        assertEquals(attachmentsDtos.getFileName(), attachments.getFileName());
        assertEquals(attachmentsDtos.getFilePath(), attachments.getFilePath());
        assertEquals(attachmentsDtos.getId(), attachments.getId());
    }

    @Test
    public void create() {
        //Given
        Attachments attachments = Attachments.builder()
                .id(1L)
                .fileName("googlePic")
                .filePath("https://google.com")
                .build();
        AttachmentsDto attachmentsDto = AttachmentsDto.builder()
                .id(1L)
                .fileName("googlePic")
                .filePath("https://google.com")
                .build();
        when(service.create(attachmentsDto)).thenReturn(attachmentsDto);
        //When
        AttachmentsDto attachmentsDtos = facade.createAttachment(attachmentsDto);
        //Then
        assertEquals(attachmentsDtos.getFileName(), attachments.getFileName());
        assertEquals(attachmentsDtos.getFilePath(), attachments.getFilePath());
        assertEquals(attachmentsDtos.getId(), attachments.getId());
    }

    @Test
    public void update() {
        //Given
        Attachments attachments = Attachments.builder()
                .id(1L)
                .fileName("googlePic")
                .filePath("https://google.com")
                .build();
        AttachmentsDto attachmentsDto = AttachmentsDto.builder()
                .id(1L)
                .fileName("googlePic")
                .filePath("https://google.com")
                .build();
        when(service.update(attachmentsDto)).thenReturn(attachmentsDto);
        //When
        AttachmentsDto attachmentsDtos = facade.updateAttachment(attachmentsDto);
        //Then
        assertEquals(attachmentsDtos.getFileName(), attachments.getFileName());
        assertEquals(attachmentsDtos.getFilePath(), attachments.getFilePath());
        assertEquals(attachmentsDtos.getId(), attachments.getId());
    }
}