package com.communicator.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Attachments {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String fileName;
    @NotNull
    private String filePath;
    @NotNull
    private String fileExtension;
    @ManyToOne
    private Message fileInMessage;
    @OneToOne
    private Users usersAttachment;
}
