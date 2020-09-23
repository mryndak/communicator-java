package com.communicator.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "_users")
public class User {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    @NotNull
    private String email;
    @NotNull
    private Date birthday;
    @NotNull
    private Date creationDate;
    @Builder.Default
    private boolean activated = false;
    @Builder.Default
    private boolean banned = false;
    @Builder.Default
    private int status = 0;
    @NotNull
    @OneToOne(cascade = CascadeType.PERSIST)
    private Attachments profilePic;
    @OneToMany(
            targetEntity = Conversation.class,
            mappedBy = "authorUser",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @Builder.Default
    private List<Conversation> authorConversation = new ArrayList<>();
    @OneToMany(
            targetEntity = Conversation.class,
            mappedBy = "receiverUser",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @Builder.Default
    private List<Conversation> receiverConversation = new ArrayList<>();
    @OneToMany(
            targetEntity = Message.class,
            mappedBy = "author",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @Builder.Default
    private List<Message> messageAuthor = new ArrayList<>();
    @OneToMany(
            targetEntity = Message.class,
            mappedBy = "receiver",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @Builder.Default
    private List<Message> messageReceiver = new ArrayList<>();
}
