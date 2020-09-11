package com.communicator.mapper;

import com.communicator.domain.FriendsList;
import com.communicator.domain.FriendsListDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ConversationMapper {
    FriendsList mapToFriendsList(FriendsListDto FriendsListDto);
    FriendsListDto mapToFriendsListDto(FriendsList FriendsList);
    List<FriendsList> mapToFriendsListList(List<FriendsListDto> FriendsListDtoList);
    List<FriendsListDto> mapToFriendsListDtoList(List<FriendsList> FriendsListList);
}
