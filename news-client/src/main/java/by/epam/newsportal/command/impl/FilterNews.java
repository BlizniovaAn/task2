package by.epam.newsportal.command.impl;

import by.epam.newsportal.command.Command;
import by.epam.newsportal.command.CommandException;
import by.epam.newsportal.controller.JspPageName;
import by.epam.newsportal.controller.RequestParameterName;
import by.epam.newsportal.entity.Author;
import by.epam.newsportal.entity.NewsPiece;
import by.epam.newsportal.entity.Tag;
import by.epam.newsportal.service.NewsPieceService;
import by.epam.newsportal.service.ServiceException;
import by.epam.newsportal.service.impl.NewsPieceServiceImpl;
import by.epam.newsportal.service.search_criteria.SearchCriteria;
import by.epam.newsportal.utils.PaginationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hanna_Blizniova on 8/15/2016.
 */
public class FilterNews implements Command {
    public static final String COMMA = ",";
    @Autowired
    private ApplicationContext appContext;
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        NewsPieceService service = (NewsPieceServiceImpl)appContext.getBean("NewsPieceServiceImpl");
        Author author = getAuthor(Long.valueOf(request.getParameter("authorId")));
        List<Tag> tags = getTags(request.getParameter("tagsId"));
        SearchCriteria criteria = new SearchCriteria(author,tags);
        List<NewsPiece> news;
        try {
            news = service.search(criteria);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        request.setAttribute(RequestParameterName.NEWS_LIST,news);
        PaginationManager.getPieceOfNews(request,news);
        String page = JspPageName.getPage(request.getParameter(RequestParameterName.PAGE));
        return page;
    }
    private Author getAuthor(Long id){
        Author author = new Author();
        author.setAuthorId(id);
        return author;
    }
    private List<Tag> getTags(String tagsId){
        List<Tag> tags = new ArrayList<>();
        if(tagsId.isEmpty()){
            return tags;
        }
        String[] arrTagsId = tagsId.split(COMMA);
        for (int i = 0 ; i < arrTagsId.length ; i++){
            Tag curTag = new Tag();
            curTag.setTagId(Long.valueOf(arrTagsId[i]));
            tags.add(curTag);
        }
        return tags;
    }
}
