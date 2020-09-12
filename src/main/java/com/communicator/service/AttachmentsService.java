package com.communicator.service;

import com.communicator.domain.Attachments;
import com.communicator.domain.AttachmentsDto;
import com.communicator.exception.AttachmentDontExistsException;
import com.communicator.exception.AttachmentNotFoundException;
import com.communicator.mapper.AttachmentsMapper;
import com.communicator.service.repository.AttachmentsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttachmentsService {
    private final AttachmentsRepository repository;
    private final AttachmentsMapper mapper;

    public List<AttachmentsDto> getAll(){
        return mapper.mapToAttachmentsDtoList(repository.findAll());
    }

    public AttachmentsDto getById(Long id) {
        return mapper.mapToAttachmentsDto(repository.findById(id).orElseThrow(AttachmentNotFoundException::new));
    }

    public AttachmentsDto create(AttachmentsDto attachmentsDto){
        Attachments mappedAttachments = mapper.mapToAttachments(attachmentsDto);
        Attachments savedAttachments = repository.save(mappedAttachments);
        return mapper.mapToAttachmentsDto(savedAttachments);
    }

    public AttachmentsDto update(AttachmentsDto attachmentsDto){
        if(attachmentsDto.getId() != null){
            isAttachmentExisting(attachmentsDto.getId());
        }
        Attachments mappedAttachments = mapper.mapToAttachments(attachmentsDto);
        Attachments savedAttachments = repository.save(mappedAttachments);
        return mapper.mapToAttachmentsDto(savedAttachments);
    }

    public void delete(AttachmentsDto attachmentsDto){
        if(attachmentsDto.getId() != null){
            isAttachmentExisting(attachmentsDto.getId());
        }
        Attachments mappedAttachments = mapper.mapToAttachments(attachmentsDto);
        repository.delete(mappedAttachments);
    }

    private void isAttachmentExisting(Long userId) {
        if(!repository.existsById(userId)){
            throw new AttachmentDontExistsException();
        }
    }
}
