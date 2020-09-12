package com.communicator.facade.logic;

import com.communicator.domain.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserStatusToggle {
    public UserDto statusChanger(UserDto userDto, boolean activated, boolean value){
        if(activated){
            if(userDto.isActivated() != value){
                userDto.setActivated(value);
            }
        }else{
            if(userDto.isBanned() != value){
                userDto.setBanned(value);
            }
        }
        return userDto;
    }
    public UserDto statusChanger(UserDto userDto, int status){
        userDto.setStatus(status);
        return userDto;
    }
}
