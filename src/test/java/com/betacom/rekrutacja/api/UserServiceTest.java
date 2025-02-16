package com.betacom.rekrutacja.api;

import com.betacom.rekrutacja.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Test
    void shouldGetUserByLogin() {

        // given
        final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        final String login = "user1";
        final String password = "password1";
        final String encodedPassword = passwordEncoder.encode(password);
        final String id = "d5be5b9d-5a65-4e31-9f5b-5daeff044ae1";
        final User user = new User(login, encodedPassword);
        user.setId(id);
        final UserRepository userRepository = mock(UserRepository.class);
        final UserService userService = new UserService(userRepository, passwordEncoder);

        // when
        when(userService.getUserByLogin(login)).thenReturn(user);

        // then
        assertThat(user.getLogin()).isEqualTo(login);
        assertTrue(passwordEncoder.matches(password, user.getPassword()));
        assertThat(user.getId()).isEqualTo(id);
    }
}
