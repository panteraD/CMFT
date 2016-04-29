package ru.kir.status.dao;

import ru.kir.status.domain.User;

import java.util.List;

/**
 * Created by Kirill Zhitelev on 07.03.2016.
 */
public interface UserDao {
    void addUser(User user);

    User getUser(String byWhat, String value);

    List<User> getAllUsers();
}
