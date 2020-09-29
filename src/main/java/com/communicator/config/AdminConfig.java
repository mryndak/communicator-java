package com.communicator.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AdminConfig {
    @Value("${admin.mail}")
    private String adminMail;
    @Value("${admin.name}")
    private String adminName;
    @Value("${admin.address.street}")
    private String adminStreet;
    @Value("${admin.address.number}")
    private String adminStreetNumber;
}