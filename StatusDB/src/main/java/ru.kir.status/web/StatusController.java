package ru.kir.status.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.kir.status.common.AttributeType;
import ru.kir.status.domain.Param;
import ru.kir.status.domain.User;
import ru.kir.status.service.ParamService;
import ru.kir.status.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static ru.kir.status.common.AttributeType.*;

/**
 * Created by Andrey on 14.03.2016.
 */

@RestController  //@RestController allows u lower @ResponseBody
@EnableWebMvc
@RequestMapping("/data")
public class StatusController {

    @Autowired
    private ParamService paramService;

    @Autowired
    private UserService userService;


    @JsonView(Views.Public.class)

    @RequestMapping(value = "/{userName}", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    UserPojo getStartupData(@PathVariable String userName) {
        UserPojo user = new UserPojo();
        user.setBegin(paramService.getParam(AttributeType.BEGIN, LOGIN, userName).getValue());
        user.setEnd(paramService.getParam(AttributeType.END, LOGIN, userName).getValue());
        user.setAddress(paramService.getParam(AttributeType.ADDRESS, LOGIN, userName).getValue());
        user.setStatus(paramService.getParam(AttributeType.STATUS_TYPE, LOGIN, userName).getValue());
        return user;
    }


    @RequestMapping(value = "/send", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity updateUserData(@RequestBody UserPojo userJson) {

        String userName = this.getUserName();
        paramService.updateParam(AttributeType.BEGIN, LOGIN, userName, userJson.begin);
        paramService.updateParam(AttributeType.END, LOGIN, userName, userJson.end);
        paramService.updateParam(AttributeType.ADDRESS, LOGIN, userName, userJson.address);
        paramService.updateParam(AttributeType.STATUS_TYPE, LOGIN, userName, userJson.status);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/intersection", method = RequestMethod.GET, produces = "application/json")
    public List<UserPojo> getIntersectionUsers() {
        String userName = this.getUserName();
        List<User> users = userService.getIntersectingUsers(userName);
        List<UserPojo> usersToSend = new ArrayList<>();
        for (User user : users) {
            usersToSend.add(new UserPojo(
                    user.getFullName(),
                    user.getLogin(),
                    paramService.getParam(AttributeType.BEGIN, LOGIN, user.getLogin()).getValue(),
                    paramService.getParam(AttributeType.END, LOGIN, user.getLogin()).getValue(),
                    paramService.getParam(AttributeType.STATUS_TYPE, LOGIN, user.getLogin()).getValue(),
                    paramService.getParam(AttributeType.ADDRESS, LOGIN, user.getLogin()).getValue(),
                    paramService.getParam(AttributeType.E_MAIL, LOGIN, user.getLogin()).getValue())
            );
        }
        return usersToSend;
    }

    /**
     * gets Current authificcated username
     *
     * @return
     */
    public String getUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((UserDetails) principal).getUsername();
    }


}