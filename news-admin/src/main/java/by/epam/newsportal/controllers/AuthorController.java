package by.epam.newsportal.controllers;

import by.epam.newsportal.entity.Author;
import by.epam.newsportal.service.AuthorService;
import by.epam.newsportal.service.ServiceException;
import by.epam.newsportal.service.impl.AuthorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Hanna_Blizniova on 8/31/2016.
 */
@Controller
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @RequestMapping(value="/test", method = RequestMethod.GET)
    public String getAuthor(ModelMap model) {
        /*Author author = new Author();
        author.setAuthorId(1L);
        Author test = null;
        try {
           test = authorService.select(author);
            System.out.println(test);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        model.addAttribute("author",test);*/
        return "login";
    }
}
