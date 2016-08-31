package by.epam.newsportal.controller;

/**
 * Created by Hanna_Blizniova on 8/3/2016.
 */
public final class JspPageName {
    public JspPageName(){}
    public static final String TECHNICAL_ERROR_PAGE = "";
    public static final String NO_SUCH_PAGE = "";
    public static final String USER_GENERAL_PAGE = "pages/user_general_page.jsp";
    public static final String CURRENT_NEWS = "/current-news.tiles";
    public static final String INDEX = "/index.tiles";

    public static String getPage(String page) {
        Pages currentPage = Pages.valueOf(page);
        switch (currentPage){
            case INDEX:{
                return JspPageName.INDEX;
            }
            case CURRENT_NEWS:{
                return JspPageName.CURRENT_NEWS;
            }
            default:{
                return JspPageName.NO_SUCH_PAGE;
            }
        }
    }
}
enum Pages{
     INDEX, CURRENT_NEWS
}
