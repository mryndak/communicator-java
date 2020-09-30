package com.communicator.facade;

import com.communicator.domain.*;
import com.communicator.service.AttachmentsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class AttachmentsFacade {
    private final AttachmentsService service;

    public List<AttachmentsDto> getAllAttachments(){
        return service.getAll();
    }

    public AttachmentsDto getAttachmentById(Long id){
        return service.getById(id);
    }

    public AttachmentsDto createAttachment(AttachmentsDto attachmentsDto){
        return service.create(attachmentsDto);
    }

    public AttachmentsDto updateAttachment(AttachmentsDto attachmentsDto){
        return service.update(attachmentsDto);
    }

    public void deleteAttachment(Long id){
        service.delete(id);
    }

}
