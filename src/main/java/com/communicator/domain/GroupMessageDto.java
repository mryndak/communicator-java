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
public class GroupMessageDto {
    private Long id;
    private List<UserConvDto> usersInConv;
    private List<MessageBlopDto> messagesInConv;
    private String customName;
    private AttachmentsDto groupPicture;
}
