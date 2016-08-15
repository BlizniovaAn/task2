package by.epam.newsportal.controller;

/**
 * Created by Hanna_Blizniova on 8/3/2016.
 */
public final class JspPageName {
    public JspPageName(){}
    public static final String TECHNICAL_ERROR_PAGE = "";
    public static final String NO_SUCH_PAGE = "";
    public static final String USER_GENERAL_PAGE = "pages/user_general_page.jsp";
    public static final String INDEX = "index.jsp";

    public static String getPage(String page) {
        Pages currentPage = Pages.valueOf(page);
        switch (currentPage){
            case USER_GENERAL_PAGE:{
                return JspPageName.USER_GENERAL_PAGE;
            }
            case INDEX:{
                return JspPageName.INDEX;
            }
            default:{
                return JspPageName.NO_SUCH_PAGE;
            }
        }
    }
}
enum Pages{
    USER_GENERAL_PAGE, INDEX
}
