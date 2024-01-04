package ru.socialnetwork.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.socialnetwork.dao.UserDao;
import ru.socialnetwork.model.User;

import java.util.List;

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

    @Override
    public List<User> getUsersByFirstNameAndLastName(String firstName, String lastName) {
        List<User> usersByFirstNameAndLastName = userDao.getUsersByFirstNameAndLastName(firstName, lastName);
        if (!usersByFirstNameAndLastName.isEmpty()) {
            return usersByFirstNameAndLastName;
        } else throw new RuntimeException(("Ошибка при поиске пользователей"));
    }
}
