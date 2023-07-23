package com.example.Book.now.service;

import com.example.Book.now.Entities.RoleEnum;
import com.example.Book.now.Entities.UserAccount;
import com.example.Book.now.Entities.UserProfile;
import com.example.Book.now.RequestBodies.LoginRequestBody;
import com.example.Book.now.RequestBodies.RegisterRequestBody;
import com.example.Book.now.exceptions.CannotSendEmailException;
import com.example.Book.now.exceptions.UserAlreadyExistsException;
import com.example.Book.now.repository.UserAccountRepository;
import com.example.Book.now.repository.UserProfileRepository;
import com.example.Book.now.responseBodies.AuthenticationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserProfileRepository userProfileRepository;
    private final MailJwtService mailJwtService;
    private final MailService mailService;

    public UserAccount findUserAccountByEmail(String email){
        return userAccountRepository.findUserAccountByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("Error"));
    };

    public AuthenticationDTO loginUserAccount(LoginRequestBody request){
        var authUser = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var jwtToken = jwtService.generateToken(authUser);
        return new AuthenticationDTO(jwtToken, Boolean.TRUE);
    }

    public AuthenticationDTO registerUserAccount(RegisterRequestBody requestBody) throws UserAlreadyExistsException, CannotSendEmailException {
        if (userAccountRepository.findUserAccountByEmailIgnoreCase(requestBody.getEmail()).isPresent()){
            throw new UserAlreadyExistsException();
        }
        UserAccount userAccount = new UserAccount();
        userAccount.setEmail(requestBody.getEmail());
        userAccount.setPassword(passwordEncoder.encode(requestBody.getPassword()));
        userAccount.setEmailVerified(false);
        RoleEnum role = RoleEnum.valueOf("USER");
        userAccount.setRole(role);
        userAccount.setLastLogin(new Date());
        UserAccount savedUserAccount = userAccountRepository.save(userAccount);

        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(savedUserAccount);
        userProfile.setCity(requestBody.getCity());
        userProfile.setPhone(requestBody.getPhone());
        userProfile.setState(requestBody.getState());
        userProfile.setCountry(requestBody.getCountry());
        userProfile.setFirstName(requestBody.getFirstName());
        userProfile.setLastName(requestBody.getLastName());
        userProfile.setDateOfBirth(requestBody.getDateOfBirth());
        userProfileRepository.save(userProfile);

        mailService.sendVerificationMail(userAccount.getEmail());

        return new AuthenticationDTO("A verification email has been sent to your email!", Boolean.TRUE);

    }

}
