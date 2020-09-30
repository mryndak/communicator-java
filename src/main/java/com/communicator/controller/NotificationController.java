package com.communicator.controller;

import com.communicator.domain.NotificationDto;
import com.communicator.facade.NotificationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/users/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationFacade facade;

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public NotificationDto createNotification(@RequestBody NotificationDto notificationDto){
        return facade.createNotification(notificationDto);
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public List<NotificationDto> getUsersNotifications(@PathVariable Long id){
        return facade.getAllNotifications(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteNotification(@PathVariable Long id){
        facade.deleteNotification(id);
    }

}
