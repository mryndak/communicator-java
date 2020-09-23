package com.communicator.controller;

import com.communicator.domain.Conversation;
import com.communicator.domain.ConversationDto;
import com.communicator.facade.ConversationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/conv")
@RequiredArgsConstructor
public class ConversationController {
    private final ConversationFacade facade;
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ConversationDto createConversation(@RequestBody ConversationDto conversationDto){
        return facade.createConversation(conversationDto);
    }
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ConversationDto getConversationById(@PathVariable Long id){
        return facade.fetchConversation(id);
    }
    @GetMapping(value = "/{idA}&{idR}", produces = APPLICATION_JSON_VALUE)
    public ConversationDto getConversationBetween(@PathVariable Long idA, @PathVariable Long idR){
        return facade.searchForConversation(idA, idR);
    }
    @GetMapping(value = "/user/{id}", produces = APPLICATION_JSON_VALUE)
    public List<ConversationDto> getAllConversations(@PathVariable Long id){
        return facade.searchForAllConversations(id);
    }
}
