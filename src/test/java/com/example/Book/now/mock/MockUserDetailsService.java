package com.example.Book.now.mock;

import com.example.Book.now.Entities.UserAccount;
import com.example.Book.now.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Primary
public class MockUserDetailsService implements UserDetailsService {

    @Autowired
    private UserAccountRepository userAccountRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserAccount> user = userAccountRepository.findUserAccountByEmailIgnoreCase(email);
        if (user.isPresent()){
            return user.get();
        }
        return null;
    }
}
