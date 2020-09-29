package com.communicator.mapper;

import com.communicator.domain.Message;
import com.communicator.domain.MessageBlopDto;
import com.communicator.domain.MessageDto;
import com.communicator.domain.MessageReceivedDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MessageMapper {
    Message mapToMessage(MessageBlopDto messageBlopDto);
    Message mapToMessage(MessageReceivedDto messageReceivedDto);
    Message mapToMessage(MessageDto messageDto);
    MessageBlopDto mapToMessageBlopDto(Message message);
    MessageBlopDto mapToMessageBlopDto(MessageReceivedDto messageReceivedDto);
    MessageBlopDto mapToMessageBlopDto(MessageDto messageDto);
    MessageReceivedDto mapToMessageReceivedDto(Message message);
    MessageReceivedDto mapToMessageReceivedDto(MessageBlopDto messageBlopDto);
    MessageReceivedDto mapToMessageReceivedDto(MessageDto messageDto);
    MessageDto mapToMessageDto(Message message);
    MessageDto mapToMessageDto(MessageBlopDto messageBlopDto);
    MessageDto mapToMessageDto(MessageReceivedDto messageReceivedDto);
    List<Message> mapMessageBlopDtoListToMessageList(List<MessageBlopDto> messageBlopDtoList);
    List<MessageBlopDto> mapMessageListToMessageBlopList(List<Message> messageBlopDtoList);
}
