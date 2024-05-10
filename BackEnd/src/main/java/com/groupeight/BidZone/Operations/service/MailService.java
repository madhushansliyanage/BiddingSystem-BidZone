package com.groupeight.BidZone.Operations.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class MailService {

    @Autowired
    private JavaMailSender emailSender;

    //private static final Logger logger = Logger.getLogger(com.example.SmartApparel.ZZZZZZZ.services.MailService.class.getName());



    @Async
    public void sendEmail(String subject, String recipient, String message) {
        send(subject, recipient, message);
    }

    @Async
    public void sendEmail(String subject, List<String> recipients, String message) {
        for (String recipient : recipients) {
            send(subject, recipient, message);
        }
    }

    private void send(String subject, String recipient, String message) {
        try {
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setSubject(subject);
            helper.setTo(recipient);
            helper.setText(message, true);
            emailSender.send(mimeMessage);
        } catch (MessagingException e) {
            //logger.info("Exception: " + e.getMessage());
        }
    }

}
