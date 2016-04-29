package ru.kir.register.web.controller;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kir.register.common.ParamsCheck;
import ru.kir.register.domain.Param;
import ru.kir.register.domain.User;
import ru.kir.register.service.UserService;
import ru.kir.register.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

import static ru.kir.register.common.AttributeType.*;

/**
 * Created by Kirill Zhitelev on 25.02.2016.
 */
@Controller
@RequestMapping(value = "/saving")
public class SavingController {
    private UserService userService = new UserServiceImpl();
    private ParamsCheck check = new ParamsCheck();

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity addUser(HttpServletRequest request) {
        User user = new User();

        if (check.checkEmail(userService, request) || check.checkLogin(userService, request)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        user.setFullName(request.getParameter("name") + " " + request.getParameter("surname"));

        Set<Param> paramSet = new HashSet<>();
        user.setLogin(request.getParameter("login"));
//        BCryptPasswordEncoder
        user.setPassword(request.getParameter("password"));

        user.setEnabled("true");
        user.setRole(1);


        paramSet.add(new Param(user, E_MAIL, request.getParameter("eMail")));
        paramSet.add(new Param(user, ADDRESS, request.getParameter("address")));
        paramSet.add(new Param(user, PATH_TO_IMAGE, request.getParameter("path")));
        paramSet.add(new Param(user, BIRTHDAY, request.getParameter("birthday")));
        paramSet.add(new Param(user, BEGIN, ""));
        paramSet.add(new Param(user, END, ""));
        paramSet.add(new Param(user, STATUS_TYPE, ""));

        user.setParams(paramSet);

        userService.addUser(user);

        return new ResponseEntity(HttpStatus.CREATED);
    }

}
