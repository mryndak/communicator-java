package com.communicator.controller;

import com.communicator.domain.MessageDto;
import com.communicator.facade.ConversationFacade;
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
    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public MessageDto createMessage(@RequestBody MessageDto messageDto){
        return facade.createMessage(messageDto);
    }
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public MessageDto getMessage(@PathVariable Long id){
        return facade.getMessageById(id);
    }
}
