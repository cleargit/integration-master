package com.sham.email.service;

import com.sham.common.dto.EmailData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void SimpleMail(EmailData data) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject(data.getSubject());
        mailMessage.setTo(data.getTo());
        mailMessage.setText(data.getTo());
        mailSender.send(mailMessage);
    }

    public void MimeMail(EmailData data) throws MessagingException {
        MimeMessage mailMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);
        helper.setTo(data.getTo());
        helper.setSubject(data.getSubject());
        helper.setText(data.getText(), true);
        mailSender.send(mailMessage);
    }


    @Async
    public void sendMail() {

    }
}
