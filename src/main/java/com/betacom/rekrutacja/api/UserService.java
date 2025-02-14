package com.betacom.rekrutacja.api;

import com.betacom.rekrutacja.dto.UserRequest;
import com.betacom.rekrutacja.entity.User;
import com.betacom.rekrutacja.error.UserAlreadyExistsException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public User getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public void addUser(UserRequest request) {

        if (nonNull(getUserByLogin(request.getLogin()))) {
            throw new UserAlreadyExistsException("User " + request.getLogin() + " already exists!");
        }

        String login = request.getLogin();
        String password = passwordEncoder.encode(request.getPassword());
        User user = new User(login, password);
        userRepository.save(user);
    }
}
