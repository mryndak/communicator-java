package com.communicator.facade;

import com.communicator.domain.UserConvDto;
import com.communicator.domain.UserDto;
import com.communicator.domain.UserSearchDto;
import com.communicator.facade.logic.UserStatusToggle;
import com.communicator.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserFacade {
    private final UserService service;
    private final UserStatusToggle toggle;

    public List<UserDto> getAllUsers(){
        return service.getAll();
    }

    public UserDto getUserById(Long id){
        return service.getById(id);
    }

    public UserConvDto getUserConvById(Long id){
        return service.getConvById(id);
    }

    public UserDto createUser(UserDto userDto){
        return service.create(userDto);
    }

    public UserDto updateUser(UserDto userDto){
        return service.update(userDto);
    }

    public UserDto changeActivated(Long id, boolean value){
        UserDto fetchedUser = service.getById(id);
        UserDto toggledUser = toggle.statusChanger(fetchedUser, true, value);
        return service.update(toggledUser);
    }

    public UserDto changeBanned(Long id, boolean value){
        UserDto fetchedUser = service.getById(id);
        UserDto toggledUser = toggle.statusChanger(fetchedUser, false, value);
        return service.update(toggledUser);
    }

    public UserDto changeStatus(Long id, int status){
        UserDto fetchedUser = service.getById(id);
        UserDto toggledUser = toggle.statusChanger(fetchedUser, status);
        return service.update(toggledUser);
    }

    public List<UserSearchDto> getUserByRegexPattern(Long type, String pattern) {
        log.info(String.valueOf(pattern));
        if(type == 0){
            return service.getBySingleNamePattern(pattern);
        }else if(type == 1){
            List<String> patterns = Arrays.asList(pattern.split(" "));
            return service.getByNamePattern(patterns);
        }else{
            return service.getByMailPattern(pattern);
        }
    }

    public UserDto getUserByData(String firstname, String lastname, String email) {
        return service.getUserByData(firstname, lastname, email);
    }
}
