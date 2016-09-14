package by.epam.newsportal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Hanna_Blizniova on 9/14/2016.
 */
@Controller
public class LoginController {
    @RequestMapping(value="goToLoginPage", method = RequestMethod.GET)
    public ModelAndView goToLoginPage(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }
 /*   @RequestMapping(value="login", method = RequestMethod.POST)
    public ModelAndView login(Model model) {
        ModelAndView m = new ModelAndView();
        m.setViewName("admin-news");
        return m;
    }*/
}
