package com.example.Book.now.service;

import com.example.Book.now.exceptions.CannotSendEmailException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;
    private final MailJwtService mailJwtService;

    @Value("{mail.from}")
    private String fromAddress;

    private SimpleMailMessage makeMailMessage(){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromAddress);
        return simpleMailMessage;
    }

    public void sendVerificationMail(String userEmail) throws CannotSendEmailException {
        SimpleMailMessage message = makeMailMessage();
        String verificationToken = mailJwtService.generateEmailVerificationToken(userEmail);
        message.setTo(userEmail);
        message.setSubject("Please verify your email address");
        message.setText("Welcome!\nPlease verify your email address in order to complete your registration!\nlocalhost:8080/api/v1/verify?token="+verificationToken);
        try{
            mailSender.send(message);
        }catch (MailException ex){
            throw new CannotSendEmailException();
        }
    }

}
