package com.communicator.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String firstName;
    private String lastname;
    private String email;
    private Date birthday;
    private Date creationDate;
    private boolean activated;
    private boolean banned;
    private boolean status;
    private AttachmentsDto profilePic;
}
