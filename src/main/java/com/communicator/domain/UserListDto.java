package com.communicator.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserListDto {
    private String firstname;
    private String lastname;
    private int status;
    private AttachmentsDto profilePic;
}
