package com.communicator.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "_friends_list")
public class FriendsList {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @OneToMany(
            targetEntity = User.class,
            mappedBy = "friendsList",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY)
    @Builder.Default
    private List<User> friendsList = new ArrayList<>();
}
