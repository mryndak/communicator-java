package com.communicator.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSearchDto {
    private Long id;
    private String firstname;
    private String lastname;
    private AttachmentsDto profilePic;
}
