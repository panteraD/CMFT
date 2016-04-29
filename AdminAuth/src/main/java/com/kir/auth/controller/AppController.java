package com.kir.auth.controller;

import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.kir.auth.db.DataBaseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.kir.auth.Util.*;

/**
 * Created by Kirill Zhitelev on 01.02.2016.
 */
@Controller
public class AppController {
    private ModelAndView adminModel = new ModelAndView();
    private DataBaseService service = new DataBaseService();

    @RequestMapping(value = "/")
    public String redirectToLoginPage() {
        return "redirect:login";
    }

    @RequestMapping(value = "/admin/input", method = RequestMethod.POST)
    public String insertDate(HttpServletRequest request, RedirectAttributes attributes) {
        String date = request.getParameter("inputDate");
        adminModel.addObject("enteredDate", date);
        if(date.equals("")) {
            adminModel.addObject("checkDate", "Please, enter the date");
        }

        else {

            if (!checkDate(date)) {
                adminModel.addObject("checkDate", "Incorrect date, please, check it");
            } else {
                service.insertDate(convertDateToBaseFormat(date));
                attributes.addFlashAttribute("adminDate", date);
                adminModel.addObject("checkDate", "Saved");
                adminModel.addObject("lastDate", convertDateToViewFormat(service.getLastDate()));
            }
        }

        return "redirect:/admin/input.do";
    }

    @RequestMapping(value = "/admin/input.do", method = RequestMethod.GET)
    public ModelAndView showRedirectedAdminPage() {
        return adminModel;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView showAdminPage() {
        adminModel.setViewName("adminPage");
        adminModel.addObject("lastDate", convertDateToViewFormat(service.getLastDate()));

        return adminModel;
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        adminModel.addObject("checkDate", "");
        if(auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:login?logout";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showLoginPage(@RequestParam(value = "error", required = false) String error,
                                      @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();

        if(error != null) {
            model.addObject("error", "Access denied");
        }
        if(logout != null) {
            model.addObject("message", "Logged out successfully");
        }

        model.setViewName("loginPage");

        return model;
    }

    //method, if use REST
    @RequestMapping(value = "/date", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String getDate() {
        JSONObject dateObj = new JSONObject();
        convertDateToJson(dateObj, convertDateToViewFormat(service.getLastDate()));
        return dateObj.toString();
    }

}
