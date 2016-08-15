package by.epam.newsportal.dao;

import by.epam.newsportal.entity.Author;
import by.epam.newsportal.entity.NewsPiece;
import by.epam.newsportal.entity.Tag;

import java.util.List;

public interface NewsPieceDao extends Dao<NewsPiece> {
    /**
     * This method used to get count of all news in the database.
     *
     * @return   Long                     news count
     * @throws DaoException if a SQL exception occurred
     */
    Long getNewsCount() throws DaoException;

    /**
     * This method used to find news using such parameters as author and list of tags.
     * If one of parameters are null,the method throw NullPointerException.
     *
     * @param    author                   search criteria
     * @param    tags                     search criteria
     * @return    List<NewsPiece>         found news
     * @throws DaoException if a SQL exception occurred
     */
    List<NewsPiece> find(Author author, List<Tag> tags) throws DaoException;

    /**
     * This method used to find news using such parameter as author.
     * If one of parameters are null,the method throw NullPointerException.
     *
     * @param    author                   search criteria
     * @return    List<NewsPiece>         found news
     * @throws DaoException if a SQL exception occurred
     */
    List<NewsPiece> find(Author author) throws DaoException;

    /**
     * This method used to find news using such parameter as list of tags.
     * If one of parameters are null,the method throw NullPointerException.
     *
     * @param    tags                     search criteria
     * @return    List<NewsPiece>         found news
     * @throws DaoException if a SQL exception occurred
     */
    List<NewsPiece> find(List<Tag> tags) throws DaoException;

    /**
     * This method used to select all news from the database.
     *
     * @return   List<Comment>     list of the news. If there is no news,
     *                             the method return empty list.
     * @throws DaoException if a SQL exception occurred
     */
    List<NewsPiece> selectAll() throws DaoException;

    /**
     * This method used to select one news from the database.
     *
     * @param    newsPiece         one news. It will be found and returned
     * @return   NewsPiece         the found news
     * @throws DaoException if a SQL exception occurred
     */
    NewsPiece select(NewsPiece newsPiece) throws DaoException;

    /**
     * This method used to add one news to the database.
     *
     * @param    newsPiece         one news. It will be added and returned
     * @return   NewsPiece         the added news
     * @throws DaoException if a SQL exception occurred
     */
    NewsPiece insert(NewsPiece newsPiece) throws DaoException;

    /**
     * This method used to delete one news from the database and returns boolean value:
     * if operation finished without exception, the method returns true. Otherwise it returns false.
     *
     * @param    newsPiece         one news. It will be removed
     * @return   boolean           this value means status of operation.
     * @throws DaoException if a SQL exception occurred
     */
    boolean delete(NewsPiece newsPiece) throws DaoException;

    /**
     * This method used to edit one news.
     *
     * @param    oldObject           old information about the news.
     * @param    newObject           new information about the news.
     * @return   Comment             updated comment
     * @throws DaoException if a SQL exception occurred
     */
    NewsPiece update(NewsPiece oldObject, NewsPiece newObject) throws DaoException;
}
