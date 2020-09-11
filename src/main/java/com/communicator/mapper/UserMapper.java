package com.communicator.mapper;

import com.communicator.domain.Users;
import com.communicator.domain.UsersConvDto;
import com.communicator.domain.UsersListDto;
import com.communicator.domain.UsersSearchDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    Users mapToUser(UsersConvDto usersConvDto);
    Users mapToUser(UsersSearchDto usersSearchDto);
    Users mapToUser(UsersListDto usersListDto);
    UsersConvDto mapToUserConvDto(Users users);
    UsersConvDto mapToUserConvDto(UsersSearchDto usersSearchDto);
    UsersConvDto mapToUserConvDto(UsersListDto usersListDto);
    UsersSearchDto mapToUserSearchDto(Users users);
    UsersSearchDto mapToUserSearchDto(UsersConvDto usersConvDto);
    UsersSearchDto mapToUserSearchDto(UsersListDto usersListDto);
    UsersListDto mapToUserListDto(Users users);
    UsersListDto mapToUserListDto(UsersConvDto usersConvDto);
    UsersListDto mapToUserListDto(UsersSearchDto usersSearchDto);
    List<Users> mapUserConvDtoListToUserList(List<UsersConvDto> usersConvDto);
    List<Users> mapUserSearchDtoListToUserList(List<UsersSearchDto> userConvDto);
    List<Users> mapUserListDtoListToUserList(List<UsersListDto> userConvDto);
    List<UsersConvDto> mapUserListToUserConvDtoList(List<Users> usersConvDto);
    List<UsersConvDto> mapUserSearchDtoListToUserConvDtoList(List<UsersSearchDto> userConvDto);
    List<UsersConvDto> mapUserListDtoListToUserConvDtoList(List<UsersListDto> userConvDto);
    List<UsersSearchDto> mapUserListToUserSearchDtoList(List<Users> usersConvDto);
    List<UsersSearchDto> mapUserConvDtoListToUserSearchDtoList(List<UsersConvDto> usersConvDto);
    List<UsersSearchDto> mapUserListDtoListToUserSearchDtoList(List<UsersListDto> userConvDto);
    List<UsersListDto> mapUserListToUserListDtoList(List<Users> usersConvDto);
    List<UsersListDto> mapUserConvDtoListToUserListDtoList(List<UsersConvDto> usersConvDto);
    List<UsersListDto> mapUserSearchDtoListToUserListDtoList(List<UsersSearchDto> userConvDto);
}
