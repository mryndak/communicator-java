package com.communicator.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
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
