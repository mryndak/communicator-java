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
public class Conversation {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @ManyToOne
    private Users receiverUsers;
    @NotNull
    @OneToMany(
            targetEntity = Message.class,
            mappedBy = "conversation",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY)
    private List<Message> conversationMessages;
}
