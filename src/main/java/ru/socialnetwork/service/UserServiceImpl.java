package ru.socialnetwork.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.socialnetwork.dao.UserDao;
import ru.socialnetwork.model.User;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public void createUser(User user) {
        userDao.createUser(user);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id).orElseThrow(() -> new RuntimeException("Юзер с %s id не найден".formatted(id)));
    }
}
