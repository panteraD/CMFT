package ru.kir.status.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Kirill Zhitelev on 08.03.2016.
 */
@Controller
public class LoginController {

    public static final String REDIRECT_URL = "/statusClient?id=";

    @Autowired
    private AuthenticationTrustResolver resolver;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public RedirectView redirectToPage() {
        RedirectView view = new RedirectView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (resolver.isAnonymous(authentication))
            view.setUrl("/status/login");
        else {
            view.setUrl(REDIRECT_URL +
                    ((UserDetails)authentication.getPrincipal()).getUsername());
        }

        return view;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showLoginPage(@RequestParam(value = "error", required = false) String error,
                                      @RequestParam(value = "logout", required = false) String logout,
                                      HttpServletResponse response) throws IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!resolver.isAnonymous(authentication)) {
            response.sendRedirect(REDIRECT_URL +
                    ((UserDetails)authentication.getPrincipal()).getUsername());
        }

        ModelAndView model = new ModelAndView();

        if (error != null) {
            model.addObject("error", "Access denied");
        }
        if (logout != null) {
            model.addObject("message", "Logged out successfully");
        }

        model.setViewName("loginPage");

        return model;
    }

    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public RedirectView redirectToYourPage() {
        RedirectView view = new RedirectView();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = ((UserDetails) principal).getUsername();
        view.setUrl(REDIRECT_URL + userName);
        return view;
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:login?logout";
    }

}
