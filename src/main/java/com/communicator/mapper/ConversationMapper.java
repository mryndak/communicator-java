package com.communicator.mapper;

import com.communicator.domain.Conversation;
import com.communicator.domain.ConversationDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ConversationMapper {
    Conversation mapToConversation(ConversationDto conversationDto);
    ConversationDto mapToConversationDto(Conversation conversation);
    List<Conversation> mapToConversationList(List<ConversationDto> conversationDtoList);
    List<ConversationDto> mapToConversationDtoList(List<Conversation> conversationList);
}
