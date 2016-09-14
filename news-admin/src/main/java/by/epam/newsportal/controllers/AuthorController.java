package by.epam.newsportal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Hanna_Blizniova on 8/31/2016.
 */
@Controller
@RequestMapping("admin")
public class AuthorController {
    @RequestMapping(value="login", method = RequestMethod.GET)
    public ModelAndView getAuthor(Model model) {
        ModelAndView m = new ModelAndView();
        m.setViewName("index");
    /*    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(BEANS_DECLARATION_XML);
        authorService = ctx.getBean("authorService",AuthorServiceImpl.class);*/

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
        return m;
    }
}
