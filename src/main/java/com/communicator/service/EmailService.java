package com.communicator.service;

import com.communicator.domain.Mail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
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

    public void send(final Mail mail, int type){
        log.info("Starting email preparation...");
        if(type != 0){
            try{
                MimeMessagePreparator mimeMessage = createMimeMessage(mail);
                javaMailSender.send(mimeMessage);
            }catch (MailException e){
                log.error("Failed to process email sending: {}", e.getMessage());
            }
        }else{
            SimpleMailMessage simpleMailMessage = createMailMessage(mail);
            javaMailSender.send(simpleMailMessage);
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

    private SimpleMailMessage createMailMessage(final Mail mail){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getRecipient().getEmail());
        mailMessage.setSubject(dailySubject);
        mailMessage.setText(String.valueOf(mail.getUnreadMessage()));
        return mailMessage;
    }
}
