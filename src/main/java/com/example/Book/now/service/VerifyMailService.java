package com.example.Book.now.service;

import com.example.Book.now.Entities.UserAccount;
import com.example.Book.now.exceptions.EmailAlreadyVerifiedException;
import com.example.Book.now.exceptions.ResourceNotFoundException;
import com.example.Book.now.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerifyMailService {

    private final MailJwtService mailJwtService;
    private final UserAccountRepository userAccountRepository;

    public String verifyEmail(String emailJwtToken) throws ResourceNotFoundException, EmailAlreadyVerifiedException {
        String userMail = mailJwtService.getEmailJwtSubject(emailJwtToken);
        UserAccount userAccount = userAccountRepository.findUserAccountByEmailIgnoreCase(userMail).orElseThrow(() -> new ResourceNotFoundException("User"));
        if (userAccount.getEmailVerified()){
            throw new EmailAlreadyVerifiedException();
        }
        userAccount.setEmailVerified(true);
        userAccountRepository.save(userAccount);
        return "Email verified!";
    }

}
