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
@Table(name = "_message")
public class Message {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @ManyToOne
    private User author;
    @NotNull
    private String content;
    @Builder.Default
    private boolean read = false;
    @OneToMany(
            fetch = FetchType.EAGER)
    private List<Attachments> attachmentsList;
    @ManyToOne
    private GroupMessage groupMessage;
}
