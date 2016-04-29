package ru.kir.register.service;

import ru.kir.register.domain.Param;
import ru.kir.register.domain.User;

/**
 * Created by Kirill Zhitelev on 16.02.2016.
 */
public interface UserService {
    void addUser(User user);
    Param getParam(String paramValue);
}
