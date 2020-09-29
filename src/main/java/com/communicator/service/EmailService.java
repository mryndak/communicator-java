package com.communicator.service;

import com.communicator.domain.Mail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final MailCreatorService mailCreatorService;

    private final static String dailySubject = "Masz nowe nieprzeczytane wiadomości";

    public void send(final Mail mail){
        log.info("Starting email preparation...");
        try{
            MimeMessagePreparator mimeMessage = createMimeMessage(mail);
            javaMailSender.send(mimeMessage);
        }catch (MailException e){
            log.error("Failed to process email sending: {}", e.getMessage());
        }
    }

    private MimeMessagePreparator createMimeMessage(final Mail mail){
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getRecipient().getEmail());
            messageHelper.setSubject(dailySubject);
            messageHelper.setText(mailCreatorService.buildDailyReportMail(mail), true);
        };
    }
}
