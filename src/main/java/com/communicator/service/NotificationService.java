package com.communicator.service;

import com.communicator.domain.*;
import com.communicator.exception.*;
import com.communicator.mapper.NotificationMapper;
import com.communicator.mapper.UserMapper;
import com.communicator.service.repository.NotificationRepository;
import com.communicator.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    private final NotificationRepository repository;
    private final NotificationMapper mapper;

    public NotificationDto create(NotificationDto notificationDto){
        return mapper.mapToNotificationDto(repository.save(mapper.mapToNotification(notificationDto)));
    }

    public void delete(Long id){
        try{
            isNotificationExisting(id);
            repository.deleteById(id);
        }catch (NotificationDontExistsException e){
            log.info(e.getMessage());
        }
    }



    private void isNotificationExisting(Long id) {
        try{
            if(!repository.existsById(id)){
                throw new NotificationDontExistsException();
            }
        }catch (MessageDontExistsException e){
            log.error(e.getMessage());
        }
    }

    public List<NotificationDto> getAllByReceiverId(Long id) {
        return mapper.mapToNotificationDtoList(repository.findAllByReceiverId(id));
    }
}
