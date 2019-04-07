package org.hackathon.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MailBox {
    private final MailSender sender;
    private final MailingProperties properties;

    @Autowired
    public MailBox(MailSender sender, MailingProperties properties) {
        this.sender = sender;
        this.properties = properties;
    }

    public void sendMail(List<String> recipients, String title, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(properties.getUsername());
        message.setSubject(title);
        message.setText(body);

        for (String recipient : recipients) {
            message.setTo(recipient);
            sender.send(message);
        }
    }
}
