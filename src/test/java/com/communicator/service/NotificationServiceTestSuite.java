package com.communicator.service;

import com.communicator.domain.*;
import com.communicator.mapper.NotificationMapper;
import com.communicator.service.repository.NotificationRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class NotificationServiceTestSuite {

    @Mock
    private NotificationRepository repository;
    @InjectMocks
    private NotificationService service;
    @Mock
    private NotificationMapper mapper;

    @Test
    public void create() {
        //Given
        User user = User.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .email("szewczykjan2@gmail.com")
                .build();
        UserConvDto usersDto = UserConvDto.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .build();
        Notification notification = Notification.builder()
                .id(1L)
                .typeOfOperation("newConversation")
                .receiver(user)
                .operationsParameters(Collections.singletonList("2"))
                .build();
        NotificationDto notificationDto = NotificationDto.builder()
                .id(1L)
                .typeOfOperation("newConversation")
                .receiver(usersDto)
                .operationsParameters(Collections.singletonList("2"))
                .build();
        when(mapper.mapToNotification(notificationDto)).thenReturn(notification);
        when(mapper.mapToNotificationDto(notification)).thenReturn(notificationDto);
        given(repository.save(notification)).willReturn(notification);
        //When
        NotificationDto fetchedNotificationDto = service.create(notificationDto);
        //Given
        assertEquals(fetchedNotificationDto.getId(), notification.getId());
        assertEquals(fetchedNotificationDto.getTypeOfOperation(), notification.getTypeOfOperation());
        assertEquals(fetchedNotificationDto.getReceiver().getId(),notification.getReceiver().getId());
        assertEquals(fetchedNotificationDto.getReceiver().getFirstname(),notification.getReceiver().getFirstname());
        assertEquals(fetchedNotificationDto.getReceiver().getLastname(),notification.getReceiver().getLastname());
    }

    @Test
    public void getAllByReceiverId() {
        //Given
        List<NotificationDto> notificationsDto = new ArrayList<>();
        List<Notification> notifications = new ArrayList<>();
        User user = User.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .email("szewczykjan2@gmail.com")
                .build();
        UserConvDto usersDto = UserConvDto.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .build();
        Notification notification = Notification.builder()
                .id(1L)
                .typeOfOperation("newConversation")
                .operationsParameters(Collections.singletonList("2"))
                .build();
        notification.setReceiver(user);
        notifications.add(notification);
        NotificationDto notificationDto = NotificationDto.builder()
                .id(1L)
                .typeOfOperation("newConversation")
                .receiver(usersDto)
                .operationsParameters(Collections.singletonList("2"))
                .build();
        notificationsDto.add(notificationDto);
        when(mapper.mapToNotification(notificationDto)).thenReturn(notification);
        when(mapper.mapToNotificationDtoList(notifications)).thenReturn(notificationsDto);
        given(repository.findAllByReceiverId(usersDto.getId())).willReturn(notifications);
        //When
        List<NotificationDto> fetchedNotificationDto = service.getAllByReceiverId(usersDto.getId());
        //Given
        assertEquals(fetchedNotificationDto.get(0).getId(), notification.getId());
        assertEquals(fetchedNotificationDto.get(0).getTypeOfOperation(), notification.getTypeOfOperation());
        assertEquals(fetchedNotificationDto.get(0).getReceiver().getId(),notification.getReceiver().getId());
        assertEquals(fetchedNotificationDto.get(0).getReceiver().getFirstname(),notification.getReceiver().getFirstname());
        assertEquals(fetchedNotificationDto.get(0).getReceiver().getLastname(),notification.getReceiver().getLastname());
    }
}