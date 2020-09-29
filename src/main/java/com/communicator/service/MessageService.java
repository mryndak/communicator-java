package com.communicator.service;

import com.communicator.domain.Message;
import com.communicator.domain.MessageDto;
import com.communicator.exception.MessageDontExistsException;
import com.communicator.exception.MessageNotFoundException;
import com.communicator.mapper.MessageMapper;
import com.communicator.service.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageService {
    private final MessageRepository repository;
    private final MessageMapper mapper;

    public MessageDto create(MessageDto messageDto){
        Message mappedMessage = mapper.mapToMessage(messageDto);
        Message savedMessage = repository.save(mappedMessage);
        repository.createMessageInConv(messageDto.getGroupMessage().getId(), savedMessage.getId());
        return mapper.mapToMessageDto(savedMessage);
    }

    private void isAttachmentExisting(Long userId) {
        if(!repository.existsById(userId)){
            throw new MessageDontExistsException();
        }
    }

    public MessageDto changeToRead(Long id) {
        if(id != null){
            isAttachmentExisting(id);
        }else{
            throw new MessageNotFoundException();
        }
        Message fetchedMessage = repository.getOne(id);
        fetchedMessage.setRead(true);
        Message savedMessage = repository.save(fetchedMessage);
        return mapper.mapToMessageDto(savedMessage);
    }
}
