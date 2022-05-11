package com.moldovan.uni.bookingsystem.services;

import com.moldovan.uni.bookingsystem.service.EmailService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailServiceTest {

    @Test
    public void shouldSendEmailWithCorrectDate(){
        //ARRANGE
        JavaMailSender javaMailSenderMock= Mockito.mock(JavaMailSender.class);
        EmailService emailService = new EmailService(javaMailSenderMock);
        //ACT
        String givenEmail = "test@yahoo.com";
        String givenSubject = "testSubject";
        String givenText = "some test text !";
        emailService.sendSimpleMessage(givenEmail, givenSubject, givenText);

        SimpleMailMessage expectedMessage = new SimpleMailMessage();
        expectedMessage.setFrom("alex.geo.best30@gmail.com");
        expectedMessage.setTo(givenEmail);
        expectedMessage.setSubject(givenSubject);
        expectedMessage.setText(givenText);
        //ASSERT
        Mockito.verify(javaMailSenderMock).send(Mockito.eq(expectedMessage));
    }
}
