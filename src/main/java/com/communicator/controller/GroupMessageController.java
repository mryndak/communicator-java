package com.communicator.controller;

import com.communicator.domain.GroupMessageDto;
import com.communicator.facade.GroupMessageFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/conv")
@RequiredArgsConstructor
public class GroupMessageController {
    private final GroupMessageFacade facade;
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public GroupMessageDto createConversation(@RequestBody GroupMessageDto groupMessageDto){
        return facade.createConversation(groupMessageDto);
    }
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public GroupMessageDto getConversationById(@PathVariable Long id){
        return facade.fetchConversation(id);
    }
    @GetMapping(value = "/user/{id}", produces = APPLICATION_JSON_VALUE)
    public List<Long> getAllConversations(@PathVariable Long id){
        return facade.searchForAllConversations(id);
    }
    @GetMapping(value = "{convId}/unread/{userId}", produces = APPLICATION_JSON_VALUE)
    public Integer countUnRead(@PathVariable Long convId, @PathVariable Long userId){
        return facade.getUnReadMessages(convId, userId);
    }
}
