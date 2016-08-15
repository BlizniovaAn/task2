package by.epam.newsportal.utils;

import by.epam.newsportal.controller.RequestParameterName;
import by.epam.newsportal.entity.NewsPiece;
import org.springframework.beans.support.PagedListHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Hanna_Blizniova on 8/15/2016.
 */
public final class PaginationManager {
    public PaginationManager(){}
    public static final Integer FIRST_PAGE = 1;
    public static final Integer COUNT_NEWS_ON_PAGE = 3;
    public static void getPieceOfNews(HttpServletRequest request, List<NewsPiece> news){
        String strPage = request.getParameter(RequestParameterName.PAGE);
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
}
