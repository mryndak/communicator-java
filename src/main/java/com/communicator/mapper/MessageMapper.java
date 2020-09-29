package com.communicator.mapper;

import com.communicator.domain.Message;
import com.communicator.domain.MessageBlopDto;
import com.communicator.domain.MessageDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MessageMapper {
    Message mapToMessage(MessageBlopDto messageBlopDto);
    Message mapToMessage(MessageDto messageDto);
    MessageBlopDto mapToMessageBlopDto(Message message);
    MessageDto mapToMessageDto(Message message);
    List<Message> mapMessageBlopDtoListToMessageList(List<MessageBlopDto> messageBlopDtoList);
    List<MessageBlopDto> mapMessageListToMessageBlopList(List<Message> messageBlopDtoList);
}
