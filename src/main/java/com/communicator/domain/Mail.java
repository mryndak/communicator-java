package com.communicator.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class Mail {
    private final UserDto recipient;
    private final Long unreadMessage;
}
