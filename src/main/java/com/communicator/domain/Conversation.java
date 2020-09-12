package com.communicator.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "_conversation")
public class Conversation {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @ManyToOne
    private User receiverUser;
    @NotNull
    @OneToMany(
            targetEntity = Message.class,
            mappedBy = "conversation",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Message> conversationMessages;
}
