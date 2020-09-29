package com.communicator.controller;

import com.communicator.domain.MessageDto;
import com.communicator.facade.MessageFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/conv/msg")
@RequiredArgsConstructor
public class MessageController {
    private final MessageFacade facade;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public MessageDto createMessage(@RequestBody MessageDto messageDto){
        return facade.createMessage(messageDto);
    }

    @PutMapping(value = "/read/{id}",consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public MessageDto updateMessage(@PathVariable Long id){
        return facade.updateMessage(id);
    }
}
