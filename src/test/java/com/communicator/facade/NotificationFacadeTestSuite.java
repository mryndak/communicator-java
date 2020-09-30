package com.communicator.facade;

import com.communicator.domain.*;
import com.communicator.service.NotificationService;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class NotificationFacadeTestSuite {

    @Mock
    private NotificationService service;
    @InjectMocks
    private NotificationFacade facade;

    @Test
    public void createNotification() {
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
        when(service.create(notificationDto)).thenReturn(notificationDto);
        //When
        NotificationDto fetchedNotificationDto = facade.createNotification(notificationDto);
        //Given
        assertEquals(fetchedNotificationDto.getId(), notification.getId());
        assertEquals(fetchedNotificationDto.getTypeOfOperation(), notification.getTypeOfOperation());
        assertEquals(fetchedNotificationDto.getReceiver().getId(),notification.getReceiver().getId());
        assertEquals(fetchedNotificationDto.getReceiver().getFirstname(),notification.getReceiver().getFirstname());
        assertEquals(fetchedNotificationDto.getReceiver().getLastname(),notification.getReceiver().getLastname());
    }

    @Test
    public void getAllNotifications() {
        //Given
        List<NotificationDto> notificationsDto = new ArrayList<>();
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
        notificationsDto.add(notificationDto);
        given(service.getAllByReceiverId(usersDto.getId())).willReturn(notificationsDto);
        //When
        List<NotificationDto> fetchedNotificationDto = facade.getAllNotifications(usersDto.getId());
        //Given
        assertEquals(fetchedNotificationDto.get(0).getId(), notification.getId());
        assertEquals(fetchedNotificationDto.get(0).getTypeOfOperation(), notification.getTypeOfOperation());
        assertEquals(fetchedNotificationDto.get(0).getReceiver().getId(),notification.getReceiver().getId());
        assertEquals(fetchedNotificationDto.get(0).getReceiver().getFirstname(),notification.getReceiver().getFirstname());
        assertEquals(fetchedNotificationDto.get(0).getReceiver().getLastname(),notification.getReceiver().getLastname());
    }
}