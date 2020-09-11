package com.communicator.mapper;

import com.communicator.domain.FriendsList;
import com.communicator.domain.FriendsListDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = UserMapper.class)
public interface FriendsListMapper{
    @Mapping(target = "friendsList", expression = "java(userMapper.mapUserListDtoListToUserList(friendsListDto.getFriendsList()))")
    FriendsList mapToFriendList(FriendsListDto friendsListDto);
    FriendsListDto mapToFriendList(FriendsList friendsList);
    List<FriendsList> mapToFriendsListList(List<FriendsListDto> friendsListDtoList);
    List<FriendsListDto> mapToFriendsListDtoList(List<FriendsList> friendsListList);
}
