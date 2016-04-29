package ru.kir.register.common;

import ru.kir.register.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Kirill Zhitelev on 25.02.2016.
 */
public class ParamsCheck {
    public boolean checkEmail(UserService userService, HttpServletRequest request) {
        return userService.getParam(request.getParameter("eMail")) != null;
    }

    public boolean checkLogin(UserService userService, HttpServletRequest request) {
        return userService.getParam(request.getParameter("login")) != null;
    }
}
