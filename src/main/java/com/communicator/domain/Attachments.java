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
@Table(name = "_attachments")
public class Attachments {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String fileName;
    @NotNull
    private String filePath;
    @NotNull
    private String fileExtension;
    @ManyToOne
    private Message fileInMessage;
    @OneToOne(cascade = CascadeType.PERSIST)
    private User userAttachment;
    @OneToOne(cascade = CascadeType.PERSIST,
            fetch = FetchType.EAGER)
    private GroupMessage groupPicture;
}
