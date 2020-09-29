package com.communicator.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private Date birthday;
    private Date creationDate;
    private boolean activated;
    private boolean banned;
    private int status;
    private AttachmentsDto profilePic;
    private List<GroupMessageDto> conversations;
}
