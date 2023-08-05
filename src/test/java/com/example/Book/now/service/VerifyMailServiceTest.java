package com.example.Book.now.service;

import com.example.Book.now.Entities.UserAccount;
import com.example.Book.now.exceptions.EmailAlreadyVerifiedException;
import com.example.Book.now.exceptions.ResourceNotFoundException;
import com.example.Book.now.repository.UserAccountRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.jwt.BadJwtException;

import java.util.Optional;

@SpringBootTest
public class VerifyMailServiceTest {

    @Autowired
    private VerifyMailService verifyMailService;
    @Autowired
    private MailJwtService mailJwtService;
    @Autowired
    private UserAccountRepository userAccountRepository;

    @Test
    @Transactional
    public void verifyMailTest() throws ResourceNotFoundException, EmailAlreadyVerifiedException {
        Assertions.assertThrows(BadJwtException.class, () ->verifyMailService.verifyEmail("aaa"), "Token should be valid.");
        Assertions.assertThrows(EmailAlreadyVerifiedException.class, () -> verifyMailService.verifyEmail(mailJwtService.generateEmailVerificationToken("admin@mail.com")), "Account shouldn't be already verified");
        String response = verifyMailService.verifyEmail(mailJwtService.generateEmailVerificationToken("kpink0@telegraph.co.uk"));
        String expected = "Email verified!";
        Assertions.assertEquals(response, expected, "Email gets verified");
        Optional<UserAccount> user = userAccountRepository.findUserAccountByEmailIgnoreCase("kpink0@telegraph.co.uk");
        Assertions.assertEquals(true, user.get().getEmailVerified(), "User entry gets successfully updated");
    }
}
