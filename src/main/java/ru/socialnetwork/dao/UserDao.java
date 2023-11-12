package ru.socialnetwork.dao;

import ru.socialnetwork.model.User;

import java.util.Optional;

public interface UserDao {
    void createUser(User user);
    Optional<User> getUserById(Long id);
}
