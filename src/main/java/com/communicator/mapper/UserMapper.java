package com.communicator.mapper;

import com.communicator.domain.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User mapToUser(UserConvDto userConvDto);
    User mapToUser(UserSearchDto userSearchDto);
    User mapToUser(UserListDto userListDto);
    User mapToUser(UserDto userDto);
    UserConvDto mapToUserConvDto(User user);
    UserConvDto mapToUserConvDto(UserSearchDto userSearchDto);
    UserConvDto mapToUserConvDto(UserListDto userListDto);
    UserConvDto mapToUserConvDto(UserDto userDto);
    UserSearchDto mapToUserSearchDto(User user);
    UserSearchDto mapToUserSearchDto(UserConvDto userConvDto);
    UserSearchDto mapToUserSearchDto(UserListDto userListDto);
    UserSearchDto mapToUserSearchDto(UserDto userDto);
    UserListDto mapToUserListDto(User user);
    UserListDto mapToUserListDto(UserConvDto userConvDto);
    UserListDto mapToUserListDto(UserSearchDto userSearchDto);
    UserListDto mapToUserListDto(UserDto userDto);
    UserDto mapToUserDto(User user);
    UserDto mapToUserDto(UserConvDto user);
    UserDto mapToUserDto(UserSearchDto user);
    UserDto mapToUserDto(UserDto user);
    List<User> mapUserConvDtoListToUserList(List<UserConvDto> userConvDto);
    List<User> mapUserSearchDtoListToUserList(List<UserSearchDto> userConvDto);
    List<User> mapUserListDtoListToUserList(List<UserListDto> userConvDto);
    List<User> mapUserDtoListToUserList(List<UserDto> userConvDto);
    List<UserConvDto> mapUserListToUserConvDtoList(List<User> userConvDto);
    List<UserConvDto> mapUserSearchDtoListToUserConvDtoList(List<UserSearchDto> userConvDto);
    List<UserConvDto> mapUserListDtoListToUserConvDtoList(List<UserListDto> userConvDto);
    List<UserConvDto> mapUserDtoListToUserConvDtoList(List<UserDto> userConvDto);
    List<UserSearchDto> mapUserListToUserSearchDtoList(List<User> userConvDto);
    List<UserSearchDto> mapUserConvDtoListToUserSearchDtoList(List<UserConvDto> userConvDto);
    List<UserSearchDto> mapUserListDtoListToUserSearchDtoList(List<UserListDto> userConvDto);
    List<UserSearchDto> mapUserDtoListToUserSearchDtoList(List<UserDto> userConvDto);
    List<UserListDto> mapUserListToUserListDtoList(List<User> userConvDto);
    List<UserListDto> mapUserConvDtoListToUserListDtoList(List<UserConvDto> userConvDto);
    List<UserListDto> mapUserSearchDtoListToUserListDtoList(List<UserSearchDto> userConvDto);
    List<UserListDto> mapUserDtoListToUserListDtoList(List<UserDto> userConvDto);
    List<UserDto> mapUserListToUserDtoList(List<User> userConvDto);
    List<UserDto> mapUserConvDtoListToUserDtoList(List<UserConvDto> userConvDto);
    List<UserDto> mapUserSearchDtoListToUserDtoList(List<UserSearchDto> userConvDto);
    List<UserDto> mapUserListDtoListToUserDtoList(List<UserListDto> userConvDto);
}
