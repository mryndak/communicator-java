package com.communicator.facade;

import com.communicator.domain.UserDto;
import com.communicator.domain.UserSearchDto;
import com.communicator.facade.logic.UserStatusToggle;
import com.communicator.mapper.UserMapper;
import com.communicator.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserFacade {
    private final UserService service;
    private final UserStatusToggle toggle;
    private final UserMapper mapper;

    public List<UserDto> getAllUsers(){
        return service.getAll();
    }

    public UserDto getUserById(Long id){
        return service.getById(id);
    }

    public UserDto createUser(UserDto userDto){
        return service.create(userDto);
    }

    public UserDto updateUser(UserDto userDto){
        return service.update(userDto);
    }

    public UserDto changeActivated(UserDto userDto, boolean value){
        UserDto toggledUser = toggle.statusChanger(userDto, true, value);
        return service.update(toggledUser);
    }

    public UserDto changeBanned(UserDto userDto, boolean value){
        UserDto toggledUser = toggle.statusChanger(userDto, false, value);
        return service.update(toggledUser);
    }

    public UserDto changeStatus(UserDto userDto, int status){
        UserDto toggledUser = toggle.statusChanger(userDto, status);
        return service.update(toggledUser);
    }

    public void deleteUser(UserDto userDto){
        service.delete(userDto);
    }

}
