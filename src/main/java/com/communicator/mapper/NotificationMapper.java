package com.communicator.mapper;

import com.communicator.domain.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NotificationMapper {
    Notification mapToNotification(NotificationDto notificationDto);
    NotificationDto mapToNotificationDto(Notification notification);
    List<Notification> mapToNotificationList(List<NotificationDto> notificationDtoList);
    List<NotificationDto> mapToNotificationDtoList(List<Notification> notificationDtoList);
}
