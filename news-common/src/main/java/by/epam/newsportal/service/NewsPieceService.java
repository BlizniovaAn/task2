package by.epam.newsportal.service;

import by.epam.newsportal.dao.DaoException;
import by.epam.newsportal.entity.Author;
import by.epam.newsportal.entity.NewsPiece;
import by.epam.newsportal.entity.Tag;
import by.epam.newsportal.service.search_criteria.SearchCriteria;

import java.util.List;
import java.util.Set;

public interface NewsPieceService extends Service<NewsPiece> {

    /**
     * This method used to add news.If one of parameters are null,
     * the method throw NullPointerException.
     *
     * @param     newsPiece          news,which will be added
     * @param     tags               news tags
     * param      authors            news authors
     * @return    NewsPiece          added news
     * @throws ServiceException if a DaoException occurred
     */
    NewsPiece addNews(NewsPiece newsPiece, Set<Tag> tags, Set<Author> authors) throws ServiceException ;

    /**
     * This method used to edit one news.
     *
     * @param    oldObject           old information about the news.
     * @param    newObject           new information about the news.
     * @return   Comment             updated comment
     * @throws ServiceException if a DaoException occurred
     */
    NewsPiece edit(NewsPiece oldObject,NewsPiece newObject) throws ServiceException ;

    /**
     * This method used to get count of all news in the database.
     *
     * @return   Long                     news count
     * @throws ServiceException if a DaoException occurred
     */
    Long getNewsCount()  throws ServiceException ;

    boolean delete(NewsPiece object) throws ServiceException;

    /**
     * This method used to find news using such parameters as search criteria.
     * If one of parameters are null,the method throw NullPointerException.
     *
     * @param     searchCriteria          container of the search criteria
     * @return    List<NewsPiece>         found news
     * @throws DaoException if a SQL exception occurred
     */
    List<NewsPiece> search(SearchCriteria searchCriteria) throws ServiceException ;

    /**
     * This method used to select all news from the database.
     *
     * @return   List<Comment>     list of the news. If there is no news,
     *                             the method return empty list.
     * @throws DaoException if a SQL exception occurred
     */
    List<NewsPiece> selectAll() throws ServiceException;

    /**
     * This method used to select one news from the database.
     *
     * @param    newsPiece         one news. It will be found and returned
     * @return   NewsPiece         the found news
     * @throws DaoException if a SQL exception occurred
     */
    NewsPiece select(NewsPiece newsPiece) throws ServiceException;

}
