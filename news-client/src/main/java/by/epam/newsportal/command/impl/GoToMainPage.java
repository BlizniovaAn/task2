package by.epam.newsportal.command.impl;

import by.epam.newsportal.command.Command;
import by.epam.newsportal.command.CommandException;
import by.epam.newsportal.controller.JspPageName;
import by.epam.newsportal.controller.RequestParameterName;
import by.epam.newsportal.entity.Author;
import by.epam.newsportal.entity.Tag;
import by.epam.newsportal.service.AuthorService;
import by.epam.newsportal.service.NewsPieceService;
import by.epam.newsportal.service.ServiceException;
import by.epam.newsportal.service.TagService;
import by.epam.newsportal.service.impl.AuthorServiceImpl;
import by.epam.newsportal.service.impl.NewsPieceServiceImpl;
import by.epam.newsportal.service.impl.TagServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Hanna_Blizniova on 8/15/2016.
 */
public class GoToMainPage implements Command {
   /* @Autowired
    private ApplicationContext appContext;*/
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
       /* List<Author> authors = getAllAuthors();
        List<Tag> tags = getAllTags();
        request.setAttribute(RequestParameterName.TAGS,tags);
        request.setAttribute(RequestParameterName.AUTHORS,authors);*/
        return JspPageName.INDEX;
    }
    /*private List<Author> getAllAuthors() throws CommandException {
        List<Author> authors;
        AuthorService service = (AuthorServiceImpl)appContext.getBean("AuthorServiceImpl");
        try {
            authors = service.selectAll();
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return authors;
    }
    private List<Tag> getAllTags() throws CommandException{
        List<Tag> tags;
        TagService service = (TagServiceImpl)appContext.getBean("TagServiceImpl");
        try {
            tags = service.selectAll();
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return tags;
    }*/
}
