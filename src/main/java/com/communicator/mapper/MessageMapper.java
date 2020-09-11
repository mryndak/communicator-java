package com.communicator.mapper;

import com.communicator.domain.Message;
import com.communicator.domain.MessageBlopDto;
import com.communicator.domain.MessageReceivedDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MessageMapper {
    Message mapToMessage(MessageBlopDto messageBlopDto);
    Message mapToMessage(MessageReceivedDto messageReceivedDto);
    MessageBlopDto mapToMessageBlopDto(Message message);
    MessageBlopDto mapToMessageBlopDto(MessageReceivedDto messageReceivedDto);
    MessageReceivedDto mapToMessageReceivedDto(Message message);
    MessageReceivedDto mapToMessageReceivedDto(MessageBlopDto messageBlopDto);
    List<Message> mapMessageBlopDtoListToMessageList(List<MessageBlopDto> messageBlopDtoList);
    List<Message> mapMessageReceivedDtoListToMessageList(List<MessageReceivedDto> messageReceivedDtoList);
    List<MessageBlopDto> mapMessageListToMessageBlopList(List<Message> messageBlopDtoList);
    List<MessageBlopDto> mapMessageReceivedDtoListToMessageBlopList(List<MessageReceivedDto> messageReceivedDtoList);
    List<MessageReceivedDto> mapMessageBlopDtoListToMessageReceivedDtoList(List<MessageBlopDto> messageBlopDtoList);
    List<MessageReceivedDto> mapMessageListToMessageReceivedDtoList(List<Message> messageReceivedDtoList);
}
