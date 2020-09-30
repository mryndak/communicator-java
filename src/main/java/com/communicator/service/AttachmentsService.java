package com.communicator.service;

import com.communicator.domain.Attachments;
import com.communicator.domain.AttachmentsDto;
import com.communicator.exception.AttachmentDontExistsException;
import com.communicator.exception.AttachmentExistsException;
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
        try{
            Attachments attachments = repository.findById(id).orElseThrow(AttachmentNotFoundException::new);
            return mapper.mapToAttachmentsDto(attachments);
        }catch (AttachmentNotFoundException e){
            log.error(e.getMessage());
        }
        return AttachmentsDto.builder().build();
    }

    public AttachmentsDto create(AttachmentsDto attachmentsDto){
        if(attachmentsDto.getId() != null){
            isAttachmentNotExisting(attachmentsDto.getId());
        }
        Attachments mappedAttachments = mapper.mapToAttachments(attachmentsDto);
        Attachments savedAttachments = repository.save(mappedAttachments);
        return mapper.mapToAttachmentsDto(savedAttachments);
    }

    public AttachmentsDto update(AttachmentsDto attachmentsDto){
        isAttachmentExisting(attachmentsDto.getId());
        Attachments mappedAttachments = mapper.mapToAttachments(attachmentsDto);
        Attachments savedAttachments = repository.save(mappedAttachments);
        return mapper.mapToAttachmentsDto(savedAttachments);
    }

    public void delete(Long id){
        isAttachmentNotExisting(id);
        repository.deleteById(id);
    }

    private void isAttachmentNotExisting(Long userId) {
        try {
            if(!repository.existsById(userId)){
                throw new AttachmentDontExistsException();
            }
        }catch (AttachmentDontExistsException e){
            log.error(e.getMessage());
        }
    }

    private void isAttachmentExisting(Long userId) {
        try {
            if(repository.existsById(userId)){
                throw new AttachmentExistsException();
            }
        }catch (AttachmentDontExistsException e){
            log.error(e.getMessage());
        }
    }
}
