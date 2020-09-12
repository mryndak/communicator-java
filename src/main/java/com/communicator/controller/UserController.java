package com.communicator.controller;

import com.communicator.domain.UserDto;
import com.communicator.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = {"${settings.cross_origin}"})
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserFacade facade;

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public UserDto getUserById(@PathVariable Long id){
        return facade.getUserById(id);
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public UserDto createUser(@RequestBody UserDto userDto){
        return facade.createUser(userDto);
    }

}
