package com.example.Book.now.service;

import com.example.Book.now.Entities.UserAccount;
import com.example.Book.now.exceptions.EmailAlreadyVerifiedException;
import com.example.Book.now.exceptions.UserNotFoundException;
import com.example.Book.now.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VerifyMailService {

    private final MailJwtService mailJwtService;
    private final UserAccountRepository userAccountRepository;

    public String verifyEmail(String emailJwtToken) throws UserNotFoundException, EmailAlreadyVerifiedException {
        String userMail = mailJwtService.getEmailJwtSubject(emailJwtToken);
        UserAccount userAccount = userAccountRepository.findUserAccountByEmailIgnoreCase(userMail).orElseThrow(() -> new UserNotFoundException());
        if (userAccount.getEmailVerified()){
            throw new EmailAlreadyVerifiedException();
        }
        userAccount.setEmailVerified(true);
        userAccountRepository.save(userAccount);
        return "Email verified!";
    }

}
