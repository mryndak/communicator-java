package com.communicator.service;

import com.communicator.config.AdminConfig;
import com.communicator.domain.Mail;
import com.communicator.domain.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class MailCreatorService {

    private final TemplateEngine templateEngine;
    private final UserService userService;
    private final AdminConfig adminConfig;

    public String buildDailyReportMail(final Mail mail){
        Context context = new Context();
        context.setVariable("userData", mail.getRecipient());
        context.setVariable("unreadMessages", mail.getUnreadMessage());
        context.setVariable("adminConfig", adminConfig);
        return templateEngine.process("mail/daily/new-mail-template.html", context);
    }
}
