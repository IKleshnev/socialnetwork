package ru.socialnetwork.service;

import ru.socialnetwork.model.User;

import java.util.Optional;

public interface UserService {
    void createUser(User user);

    User getUserById(Long id);
}
