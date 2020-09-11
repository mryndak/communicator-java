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
@Entity
public class Users {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastname;
    @NotNull
    private String email;
    @NotNull
    private Date birthday;
    @Builder.Default
    private Date creationDate = new Date();
    @Builder.Default
    private boolean activated = false;
    @Builder.Default
    private boolean banned = false;
    @Builder.Default
    private boolean status = false;
    @NotNull
    @OneToOne
    private Attachments profilePic;
    @OneToMany(
            targetEntity = Conversation.class,
            mappedBy = "receiverUsers",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY)
    private List<Conversation> conversation;
    @OneToMany(
            targetEntity = Message.class,
            mappedBy = "author",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY)
    private List<Message> messageAuthor;
    @OneToMany(
            targetEntity = Message.class,
            mappedBy = "receiver",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY)
    private List<Message> messageReceiver;
    @ManyToOne
    private FriendsList friendsList;
}
