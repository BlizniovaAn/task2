package by.epam.newsportal.command.impl;

import by.epam.newsportal.command.Command;
import by.epam.newsportal.command.CommandException;
import org.springframework.context.ApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Hanna_Blizniova on 8/15/2016.
 */
public class FilterNews1 implements Command {
    public static final String COMMA = ",";

    private ApplicationContext appContext;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
       // NewsPieceService service = (NewsPieceServiceImpl)appContext.getBean("NewsPieceServiceImpl");
        /*Author author = getAuthor(Long.valueOf(request.getParameter("authorId")));
        List<Tag> tags = getTags(request.getParameter("tagsId"));*/
       // SearchCriteria criteria = new SearchCriteria(author,tags);
       // List<NewsPiece> news;
      /*  try {
            news = service.selectAll();
        } catch (ServiceException e) {
            e.printStackTrace();
        }*/
        /*try {
            news = service.search(criteria);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }*/
       /*  request.setAttribute(RequestParameterName.NEWS_LIST,news);
        PaginationManager.getPieceOfNews(request,news);
        String page = JspPageName.getPage(request.getParameter(RequestParameterName.PAGE));
        return page;
    }*/

   /* */
        return null;
    }
   /* private Author getAuthor(Long id){
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
    }*/
}
