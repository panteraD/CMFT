package ru.kir.register.dao;

import ru.kir.register.domain.Param;
import ru.kir.register.domain.User;

/**
 * Created by Kirill Zhitelev on 16.02.2016.
 */
public interface UserDao {
    void addUser(User user);
    Param getParam(String paramValue);
}
