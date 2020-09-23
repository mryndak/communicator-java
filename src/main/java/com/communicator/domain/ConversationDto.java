package com.communicator.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConversationDto {
    private Long id;
    private UserConvDto authorUser;
    private UserConvDto receiverUser;
    private List<MessageBlopDto> conversationMessages;
}
