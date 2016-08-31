package by.epam.newsportal.command.impl;

import by.epam.newsportal.command.Command;
import by.epam.newsportal.command.CommandException;
import by.epam.newsportal.command.CommandHelper;
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
import by.epam.newsportal.utils.PaginationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Hanna_Blizniova on 8/15/2016.
 */
public class GoToMainPage implements Command {
    private final static String BEANS_DECLARATION_XML = "classpath*:spring.xml";
    public GoToMainPage(){}
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(BEANS_DECLARATION_XML);
        AuthorService authorService = ctx.getBean("authorService",AuthorServiceImpl.class);
        TagService tagService = ctx.getBean("tagService",TagServiceImpl.class);
        NewsPieceService newsPieceService = ctx.getBean("newsService",NewsPieceServiceImpl.class);
        List<Author> authors = getAllAuthors(authorService);
        List<Tag> tags = getAllTags(tagService);
        request.setAttribute(RequestParameterName.TAGS,tags);
        request.setAttribute(RequestParameterName.AUTHORS,authors);
        PaginationManager.paginate(request,newsPieceService);
        return JspPageName.INDEX;
    }
    private List<Author> getAllAuthors(AuthorService authorService) throws CommandException {
        List<Author> authors;
        try {
            authors = authorService.selectAll();
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return authors;
    }
    private List<Tag> getAllTags(TagService tagService) throws CommandException{
        List<Tag> tags;
        try {
            tags = tagService.selectAll();
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return tags;
    }
}
