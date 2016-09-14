package by.epam.newsportal.controllers;

import by.epam.newsportal.service.NewsPieceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by Hanna_Blizniova on 9/14/2016.
 */
@Controller
public class NewsController {
    @Autowired
    NewsPieceService newsPieceService;

    @RequestMapping(value="allNews", method = RequestMethod.POST)
    public ModelAndView getAllNews(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin-news");
        return modelAndView;
    }
}
