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
    @OneToOne
    @Builder.Default
    private Attachments profilePic = Attachments.builder()
            .fileName("950-9501315_katie-notopoulos-katienotopoulos-i-write-about-tech-user")
            .fileExtension("PNG")
            .filePath("https://www.pngkey.com/png/detail/950-9501315_katie-notopoulos-katienotopoulos-i-write-about-tech-user.png")
            .build();
    @OneToMany(
            targetEntity = Conversation.class,
            mappedBy = "receiverUser",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY)
    @Builder.Default
    private List<Conversation> conversation = new ArrayList<>();
    @OneToMany(
            targetEntity = Message.class,
            mappedBy = "author",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY)
    @Builder.Default
    private List<Message> messageAuthor = new ArrayList<>();
    @OneToMany(
            targetEntity = Message.class,
            mappedBy = "receiver",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY)
    @Builder.Default
    private List<Message> messageReceiver = new ArrayList<>();
    @ManyToOne
    @Builder.Default
    private FriendsList friendsList = FriendsList.builder().build();
}
