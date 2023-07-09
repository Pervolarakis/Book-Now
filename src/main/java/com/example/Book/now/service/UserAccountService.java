package com.example.Book.now.service;

import com.example.Book.now.Entities.UserAccount;
import com.example.Book.now.RequestBodies.LoginRequestBody;
import com.example.Book.now.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserAccount findUserAccountByEmail(String email){
        return userAccountRepository.findUserAccountByEmail(email)
                .orElseThrow(() -> new RuntimeException("Error"));
    };

    public String loginUserAccount(LoginRequestBody request){
        var authUser = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var jwtToken = jwtService.generateToken(authUser);
        return jwtToken;
    }

}
