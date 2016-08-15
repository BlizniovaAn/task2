package by.epam.newsportal.command.impl;

import by.epam.newsportal.command.Command;
import by.epam.newsportal.command.CommandException;
import by.epam.newsportal.controller.RequestParameterName;
import by.epam.newsportal.entity.NewsPiece;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.cglib.transform.impl.InterceptFieldCallback;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Hanna_Blizniova on 8/15/2016.
 */
public class ShowPieceNews implements Command {
    public static final Integer FIRST_PAGE = 1;
    public static final Integer COUNT_NEWS_ON_PAGE = 3;
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        return null;
    }
    private void getPieceOfNews(HttpServletRequest request, List<NewsPiece> news){
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
