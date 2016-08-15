package by.epam.newsportal.command;

/**
 * Created by Hanna_Blizniova on 8/8/2016.
 */
public final class PageHelper {
    public PageHelper(){}
    public static String preparePage(String uri){
        String pageName = uri.substring(uri.lastIndexOf("/") + 1);
        String preparedPage = pageName.trim().toUpperCase().substring(0, pageName.length() - 4);
        return preparedPage;
    }
}
