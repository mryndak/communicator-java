package com.communicator.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserConvDto {
    private Long id;
    private String firstName;
    private String lastname;
    private Date birthday;
    private int status;
    private AttachmentsDto profilePic;
}
