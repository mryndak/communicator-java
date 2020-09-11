package com.communicator.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsersListDto {
    private String firstName;
    private String lastname;
    private boolean status;
    private Attachments profilePic;
}
