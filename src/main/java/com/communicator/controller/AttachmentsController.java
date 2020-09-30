package com.communicator.controller;

import com.communicator.domain.AttachmentsDto;
import com.communicator.facade.AttachmentsFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/attachments")
@RequiredArgsConstructor
public class AttachmentsController {
    private final AttachmentsFacade facade;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<AttachmentsDto> getAllAttachments(){
        return facade.getAllAttachments();
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public AttachmentsDto getAttachmentById(@PathVariable Long id){
        return facade.getAttachmentById(id);
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public AttachmentsDto createAttachment(@RequestBody AttachmentsDto attachmentsDto){
        return facade.createAttachment(attachmentsDto);
    }

    @PutMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public AttachmentsDto updateAttachment(@RequestBody AttachmentsDto attachmentsDto){
        return facade.updateAttachment(attachmentsDto);
    }

}
