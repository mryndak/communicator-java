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
    @OneToOne(cascade = CascadeType.ALL)
    private Attachments profilePic;
    @ManyToMany(
            targetEntity = GroupMessage.class,
            mappedBy = "usersInConv",
            cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER)
    @Builder.Default
    private List<GroupMessage> conversations = new ArrayList<>();
    @OneToMany(
            targetEntity = Message.class,
            mappedBy = "author",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @Builder.Default
    private List<Message> messageAuthor = new ArrayList<>();
    @OneToMany(
            targetEntity = Notification.class,
            mappedBy = "receiver",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @Builder.Default
    private List<Notification> notifications = new ArrayList<>();
}
