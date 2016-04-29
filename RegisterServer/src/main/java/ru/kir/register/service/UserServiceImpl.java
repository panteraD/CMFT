package ru.kir.register.service;

import ru.kir.register.dao.UserDaoImpl;
import ru.kir.register.domain.Param;
import ru.kir.register.domain.User;

/**
 * Created by Kirill Zhitelev on 16.02.2016.
 */
public class UserServiceImpl implements UserService {
    private UserDaoImpl userDao = new UserDaoImpl();

    @Override
    public void addUser(User user) {
        userDao.openTransactionalCurrentSession();
        userDao.addUser(user);
        userDao.closeTransactionalCurrentSession();
    }

    @Override
    public Param getParam(String paramValue) {
        userDao.openCurrentSession();
        Param param = userDao.getParam(paramValue);
        userDao.closeCurrentSession();
        return param;
    }
}
