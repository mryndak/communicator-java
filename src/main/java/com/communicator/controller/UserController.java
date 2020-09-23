package com.communicator.controller;

import com.communicator.domain.UserConvDto;
import com.communicator.domain.UserDto;
import com.communicator.domain.UserSearchDto;
import com.communicator.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserFacade facade;

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public UserDto getUserById(@PathVariable Long id){
        return facade.getUserById(id);
    }

    @GetMapping(value = "/{id}/conv", produces = APPLICATION_JSON_VALUE)
    public UserConvDto getConvUserById(@PathVariable Long id){
        return facade.getUserConvById(id);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<UserSearchDto> getUserByRegexPattern(@RequestParam Long searchType, @RequestParam String searchValue){
        return facade.getUserByRegexPattern(searchType, searchValue);
    }

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public UserDto createUser(@RequestBody UserDto userDto){
        return facade.createUser(userDto);
    }

    @PutMapping(value = "/{id}/banned", produces = APPLICATION_JSON_VALUE)
    public UserDto changeBanStatus(@PathVariable Long id, @RequestParam boolean value){
        return facade.changeBanned(id, value);
    }

    @PutMapping(value = "/{id}/activated", produces = APPLICATION_JSON_VALUE)
    public UserDto changeActiveStatus(@PathVariable Long id, @RequestParam boolean value){
        return facade.changeActivated(id, value);
    }

    @PutMapping(value = "/{id}/status", produces = APPLICATION_JSON_VALUE)
    public UserDto changeStatusStatus(@PathVariable Long id, @RequestParam int value){
        return facade.changeStatus(id, value);
    }

}
