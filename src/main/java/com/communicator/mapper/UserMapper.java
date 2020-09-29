package com.communicator.mapper;

import com.communicator.domain.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User mapToUser(UserDto userDto);
    UserConvDto mapToUserConvDto(User user);
    UserDto mapToUserDto(User user);
    UserDataChecker mapToUserDataChecker(UserDto userDto);
    List<UserSearchDto> mapUserListToUserSearchDtoList(List<User> userConvDto);
    List<UserDto> mapUserListToUserDtoList(List<User> userConvDto);
}
