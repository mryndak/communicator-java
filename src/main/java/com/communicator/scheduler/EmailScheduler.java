package com.communicator.scheduler;

import com.communicator.domain.GroupMessageDto;
import com.communicator.domain.Mail;
import com.communicator.domain.UserDto;
import com.communicator.service.EmailService;
import com.communicator.service.GroupMessageService;
import com.communicator.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
@RequiredArgsConstructor
public class EmailScheduler {

    private final GroupMessageService groupMessageService;
    private final EmailService emailService;
    private final UserService userService;

    @Scheduled(cron = "* */5 * * * *")
    public void countUnreadMessages(){
        List<GroupMessageDto> conv = groupMessageService.getAll();
        List<UserDto> users = userService.getAll();
        HashMap<Long, AtomicInteger> countUnreadArray = new HashMap<>();
        AtomicInteger countUnread = new AtomicInteger();
        users.forEach(e-> countUnreadArray.put(e.getId(), new AtomicInteger()));
        conv.forEach(c-> c.getUsersInConv().forEach(u->{
            countUnread.set(0);
            c.getMessagesInConv().forEach(m->{
                if(!m.getAuthor().getId().equals(u.getId()) && !m.isRead()){
                    countUnread.getAndIncrement();
                }
            });
            countUnreadArray.get(u.getId()).addAndGet(countUnread.intValue());
        }));
        users.forEach(u->{
            if(countUnreadArray.get(u.getId()).intValue()>0){
                Mail mail = Mail.builder()
                        .recipient(u)
                        .unreadMessage(countUnreadArray.get(u.getId()).longValue())
                        .build();
                emailService.send(mail);
            }
        });
    }
}
