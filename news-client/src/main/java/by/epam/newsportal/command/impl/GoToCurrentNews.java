package by.epam.newsportal.command.impl;

import by.epam.newsportal.command.Command;
import by.epam.newsportal.command.CommandException;
import by.epam.newsportal.controller.JspPageName;
import by.epam.newsportal.controller.RequestParameterName;
import by.epam.newsportal.entity.NewsPiece;
import by.epam.newsportal.service.NewsPieceService;
import by.epam.newsportal.service.ServiceException;
import by.epam.newsportal.service.impl.NewsPieceServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Hanna_Blizniova on 8/29/2016.
 */
public class GoToCurrentNews implements Command {
    private final static String BEANS_DECLARATION_XML = "classpath*:spring.xml";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        NewsPiece news = getNews(request);
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(BEANS_DECLARATION_XML);
        NewsPieceService newsPieceService = ctx.getBean("newsService", NewsPieceServiceImpl.class);
        System.out.println(request.getParameter("authorId"));
        NewsPiece newsPiece;
        try {
            newsPiece = newsPieceService.select(news);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        request.setAttribute(RequestParameterName.NEWS_PIECE, newsPiece);
        return JspPageName.CURRENT_NEWS;
    }
    private NewsPiece getNews(HttpServletRequest request){
        Long newsId = Long.valueOf(request.getParameter("newsId"));
        NewsPiece newsPiece = new NewsPiece();
        newsPiece.setNewsId(newsId);
        return newsPiece;
    }
}
