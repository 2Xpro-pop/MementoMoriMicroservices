package com.example.auth.services;

import com.example.auth.models.User;
import com.example.auth.repositories.UserRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordHasher passwordHasher;
    public Optional<User> getByLogin(@NonNull String login) {
        return Optional.ofNullable(userRepository.findByLogin(login));
    }

    public boolean checkLoginAndPassword(@NonNull String login, @NonNull String password){
        var user = getByLogin(login);
        if(user.isEmpty()){
            return false;
        }
        return passwordHasher.check(user.get().getPassword(), password);
    }

    public void createUser(User user, String password){
        user.setPassword(passwordHasher.hash(password));
        userRepository.save(user);
    }

}