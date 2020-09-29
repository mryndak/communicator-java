package com.communicator.controller;

import com.communicator.facade.MessageFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/v1/conv/msg/update")
@RequiredArgsConstructor
public class CommunicationController {

    private final MessageFacade facade;

    @GetMapping("/{userId}")
    public HashMap<Long, Boolean> isNewMessageSent(@PathVariable("userId") Long userId){
        return new HashMap<>();
    }

}
