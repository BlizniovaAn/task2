package by.epam.newsportal.utils;

import by.epam.newsportal.command.CommandException;
import by.epam.newsportal.controller.RequestParameterName;
import by.epam.newsportal.entity.Author;
import by.epam.newsportal.entity.NewsPiece;
import by.epam.newsportal.entity.Tag;
import by.epam.newsportal.service.NewsPieceService;
import by.epam.newsportal.service.ServiceException;
import by.epam.newsportal.service.search_criteria.SearchCriteria;
import org.springframework.beans.support.PagedListHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hanna_Blizniova on 8/15/2016.
 */
public final class PaginationManager {
    public static final String COMMA = ",";
    public PaginationManager(){}
    public static final Integer FIRST_PAGE = 1;
    public static final Integer COUNT_NEWS_ON_PAGE = 4;

    public static void paginate(HttpServletRequest request, NewsPieceService newsPieceService) throws CommandException {
       List<NewsPiece> news = getSortedNews(request,newsPieceService);
       paginateSortedNews(request,news);
    }
    private static List<NewsPiece> getSortedNews(HttpServletRequest request, NewsPieceService newsPieceService) throws CommandException {
        System.out.println(request.getParameter("authorId"));
        System.out.println(request.getParameter("tagsId"));
        Author author = getAuthor(request.getParameter("authorId"));
        List<Tag> tags = getTags(request.getParameter("tagsId"));
        SearchCriteria criteria = new SearchCriteria(author,tags);
        List<NewsPiece> news;
        try {
            news = newsPieceService.search(criteria);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return news;
    }
    public static void paginateSortedNews(HttpServletRequest request, List<NewsPiece> news){
        String strPage = request.getParameter(RequestParameterName.PAGE_NUMBER);
        Integer pageNumber = null;
        if (strPage != null){
            pageNumber = Integer.parseInt(request.getParameter(RequestParameterName.PAGE_NUMBER));
        }
        PagedListHolder<NewsPiece> pagedListHolder = new PagedListHolder<NewsPiece>(news);
        pagedListHolder.setPageSize(COUNT_NEWS_ON_PAGE);

        request.setAttribute(RequestParameterName.MAX_PAGE,pagedListHolder.getPageCount());

        //this?
        if (pageNumber == null || pageNumber < FIRST_PAGE || pageNumber > pagedListHolder.getPageCount()){
            pageNumber = 1;
        }
        request.setAttribute(RequestParameterName.PAGE_NUMBER, pageNumber);

        if(pageNumber == null || pageNumber < FIRST_PAGE || pageNumber > pagedListHolder.getPageCount()){
            pagedListHolder.setPage(0);
            request.setAttribute(RequestParameterName.NEWS_LIST, pagedListHolder.getPageList());
        }
        else {
            pagedListHolder.setPage(pageNumber - 1);
            request.setAttribute(RequestParameterName.NEWS_LIST, pagedListHolder.getPageList());
        }
    }
    private static Author getAuthor(String id){
        Author author = null;
        if(id == null || id.equals("0")){
            return author;
        }else{
           author = new Author();
           author.setAuthorId(Long.valueOf(id));
        }
        return author;
    }
    private static List<Tag> getTags(String tagsId){
        if(tagsId == null){
            return null;
        }
        List<Tag> tags = new ArrayList<Tag>();
        if(tagsId.isEmpty()){
            return tags;
        }
        String[] arrTagsId = tagsId.split(COMMA);
        for (int i = 0; i < arrTagsId.length; i++){
            Tag curTag = new Tag();
            curTag.setTagId(Long.valueOf(arrTagsId[i]));
            tags.add(curTag);
        }
        return tags;
    }
}
