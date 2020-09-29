package com.communicator.mapper;

import com.communicator.domain.GroupMessage;
import com.communicator.domain.GroupMessageDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GroupMessageMapper {
    GroupMessage mapToGroupMessage(GroupMessageDto groupMessageDto);
    GroupMessageDto mapToGroupMessageDto(GroupMessage groupMessage);
    List<GroupMessageDto> mapToGroupMessageDtoList(List<GroupMessage> conversationList);
}
