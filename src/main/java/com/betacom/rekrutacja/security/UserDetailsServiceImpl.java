package com.betacom.rekrutacja.security;

import com.betacom.rekrutacja.api.UserRepository;
import com.betacom.rekrutacja.entity.User;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    @SneakyThrows
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        Optional<User> potentialUser = Optional.ofNullable(userRepository.findByLogin(login));
        if (potentialUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        User user = potentialUser.get();
        return org.springframework.security.core.userdetails.User.builder()
                .username(login)
                .password(user.getPassword())
                .build();
    }
}
