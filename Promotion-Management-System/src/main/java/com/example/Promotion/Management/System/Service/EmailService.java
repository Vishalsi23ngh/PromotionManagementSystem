package com.example.Promotion.Management.System.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    @Autowired
    private  JavaMailSender javaMailSender;

    public void  sendEmail(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            javaMailSender.send(message);
        } catch (Exception e) {
            // Handle exception appropriately (log it, throw custom exception, etc.)
            e.printStackTrace(); // Example: Log the exception
            throw new RuntimeException("Failed to send email");
        }
    }
}
