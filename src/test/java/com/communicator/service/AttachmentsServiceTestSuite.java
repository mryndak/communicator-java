package com.communicator.service;

import com.communicator.domain.*;
import com.communicator.mapper.AttachmentsMapper;
import com.communicator.service.repository.AttachmentsRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class AttachmentsServiceTestSuite {

    @Mock
    private AttachmentsRepository repository;
    @InjectMocks
    private AttachmentsService service;
    @Mock
    private AttachmentsMapper mapper;

    @Test
    public void getAll() {
        //Given
        List<Attachments> attachmentsList = new ArrayList<>();
        Attachments attachments = Attachments.builder()
                .id(1L)
                .fileName("googlePic")
                .filePath("https://google.com")
                .build();
        attachmentsList.add(attachments);
        attachments.setId(2L);
        attachmentsList.add(attachments);
        List<AttachmentsDto> attachmentsDtoList = new ArrayList<>();
        AttachmentsDto attachmentsDto = AttachmentsDto.builder()
                .id(1L)
                .fileName("googlePic")
                .filePath("https://google.com")
                .build();
        attachmentsDtoList.add(attachmentsDto);
        when(mapper.mapToAttachmentsDtoList(attachmentsList)).thenReturn(attachmentsDtoList);
        when(repository.findAll()).thenReturn(attachmentsList);
        //When
        List<AttachmentsDto> attachmentsDtos = service.getAll();
        //Then
        assertEquals(attachmentsDtoList.size(), attachmentsDtos.size());
    }

    @Test
    public void getById() {
        //Given
        Message message = Message.builder().build();
        User user = User.builder().build();
        MessageDto messageDto = MessageDto.builder().build();
        UserDto userDto = UserDto.builder().build();
        Attachments attachments = Attachments.builder()
                .id(1L)
                .fileName("googlePic")
                .filePath("https://google.com")
                .fileExtension("PNG")
                .fileInMessage(message)
                .userAttachment(user)
                .build();
        AttachmentsDto attachmentsDto = AttachmentsDto.builder()
                .id(1L)
                .fileName("googlePic")
                .filePath("https://google.com")
                .fileExtension("PNG")
                .fileInMessage(messageDto)
                .userAttachment(userDto)
                .build();
        when(mapper.mapToAttachmentsDto(attachments)).thenReturn(attachmentsDto);
        given(repository.findById(attachments.getId())).willReturn(Optional.of(attachments));
        //When
        AttachmentsDto attachmentsDtos = service.getById(attachments.getId());
        //Then
        assertEquals(attachmentsDto.getFileInMessage(), attachmentsDtos.getFileInMessage());
        assertEquals(attachmentsDto.getFileExtension(), attachmentsDtos.getFileExtension());
        assertEquals(attachmentsDto.getUserAttachment(), attachmentsDtos.getUserAttachment());
        assertEquals(attachmentsDto.getFileName(), attachmentsDtos.getFileName());
        assertEquals(attachmentsDto.getFilePath(), attachmentsDtos.getFilePath());
        assertEquals(attachmentsDto.getId(), attachmentsDtos.getId());
    }

    @Test
    public void create() {
        //Given
        Message message = Message.builder().build();
        User user = User.builder().build();
        Attachments attachments = Attachments.builder()
                .id(1L)
                .fileName("googlePic")
                .filePath("https://google.com")
                .fileExtension("PNG")
                .fileInMessage(message)
                .userAttachment(user)
                .build();
        AttachmentsDto attachmentsDto = AttachmentsDto.builder()
                .id(1L)
                .fileName("googlePic")
                .filePath("https://google.com")
                .build();
        when(mapper.mapToAttachmentsDto(attachments)).thenReturn(attachmentsDto);
        when(mapper.mapToAttachments(attachmentsDto)).thenReturn(attachments);
        when(repository.save(attachments)).thenReturn(attachments);
        //When
        AttachmentsDto attachmentsDtos = service.create(attachmentsDto);
        //Then
        assertEquals(attachmentsDtos.getFileName(), attachments.getFileName());
        assertEquals(attachmentsDtos.getFilePath(), attachments.getFilePath());
        assertEquals(attachmentsDtos.getId(), attachments.getId());
    }

    @Test
    public void update() {
        //Given
        Message message = Message.builder().build();
        User user = User.builder().build();
        Attachments attachments = Attachments.builder()
                .id(1L)
                .fileName("googlePic")
                .filePath("https://google.com")
                .fileExtension("PNG")
                .fileInMessage(message)
                .userAttachment(user)
                .build();
        AttachmentsDto attachmentsDto = AttachmentsDto.builder()
                .id(1L)
                .fileName("googlePic")
                .filePath("https://google.com")
                .build();
        when(mapper.mapToAttachmentsDto(attachments)).thenReturn(attachmentsDto);
        when(mapper.mapToAttachments(attachmentsDto)).thenReturn(attachments);
        when(repository.save(attachments)).thenReturn(attachments);
        //When
        AttachmentsDto attachmentsDtos = service.update(attachmentsDto);
        //Then
        assertEquals(attachmentsDtos.getFileName(), attachments.getFileName());
        assertEquals(attachmentsDtos.getFilePath(), attachments.getFilePath());
        assertEquals(attachmentsDtos.getId(), attachments.getId());
    }
}