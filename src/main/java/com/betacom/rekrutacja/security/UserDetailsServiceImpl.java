package com.betacom.rekrutacja.security;

import com.betacom.rekrutacja.api.UserRepository;
import com.betacom.rekrutacja.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        User user = userRepository.findByLogin(login);

        if (isNull(user)) throw new UsernameNotFoundException("User not found");

        return org.springframework.security.core.userdetails.User.builder()
                .username(login)
                .password(user.getPassword())
                .build();
    }
}
