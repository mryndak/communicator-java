package com.communicator.service;

import com.communicator.domain.Mail;
import com.communicator.domain.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceTestSuite {
    private final static String dailySubject = "Masz nowe nieprzeczytane wiadomości";
    @InjectMocks
    private EmailService simpleEmailService;
    @Mock
    private JavaMailSender javaMailSender;
    @Test
    public void shouldSendEmail(){
        //Given
        UserDto userDto = UserDto.builder()
                .id(1L)
                .firstname("Jan")
                .lastname("Szewczyk")
                .email("szewczykjan2@gmail.com")
                .build();
        Mail mail = new Mail(userDto, 5L);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getRecipient().getEmail());
        mailMessage.setSubject(dailySubject);
        mailMessage.setText(String.valueOf(5L));
        //When
        simpleEmailService.send(mail, 0);
        //Then
        verify(javaMailSender, times(1)).send(mailMessage);
    }
}
