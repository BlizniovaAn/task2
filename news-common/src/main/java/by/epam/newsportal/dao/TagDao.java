package by.epam.newsportal.dao;

import by.epam.newsportal.entity.NewsPiece;
import by.epam.newsportal.entity.Tag;

import java.util.List;
import java.util.Set;

public interface TagDao extends Dao<Tag> {
    /**
     * This method used to select all tags of the news.If the parameter is
     * null,the method throw NullPointerException.
     *
     * @param    newsPiece            tags of this news are found
     * @return  Set<Tag>             found tags
     * @throws DaoException if a SQL exception occurred
     */
    Set<Tag> select(NewsPiece newsPiece)  throws DaoException;

    /**
     * This method used to add new tag to the news.If one of
     * parameters are null,the method throw NullPointerException.
     *
     * @param    newsPiece            the tag will be added to this news
     * @param    tag                  the tag,which will be added to the news
     * @return   Tag                  added tag
     * @throws DaoException if a SQL exception occurred
     */
    Tag addTagToNews(NewsPiece newsPiece,Tag tag) throws DaoException;

    /**
     * This method used to select all tags from the database.
     *
     * @return   List<Tag>         list of tags. If there is no tags,
     *                             the method return empty list.
     * @throws DaoException if a SQL exception occurred
     */
    List<Tag> selectAll() throws DaoException;

    /**
     * This method used to select one tag from the database.If the parameter is
     * null,the method throw NullPointerException.
     *
     * @param    tag               one tag. It will be found and returned
     * @return   Tag               the found tag
     * @throws DaoException if a SQL exception occurred
     */
    Tag select(Tag tag) throws DaoException;

    /**
     * This method used to add one tag to the database.If the parameter is
     * null,the method throw NullPointerException.
     *
     * @param    tag               one tag. It will be added and returned
     * @return   Tag               the added tag
     * @throws DaoException if a SQL exception occurred
     */
    Tag insert(Tag tag) throws DaoException;

    /**
     * This method used to delete one tag from the database and returns boolean value:
     * if operation finished without exception, the method returns true. Otherwise it returns false.
     * If the parameter @param tag is null,the method throw NullPointerException.
     *
     * @param    tag               one tag. It will be removed
     * @return   boolean           this value means status of operation.
     * @throws DaoException if a SQL exception occurred
     */
    boolean delete(Tag tag) throws DaoException;

    /**
     * This method used to edit one tag.If one of
     * parameters are null,the method throw NullPointerException.
     *
     * @param    oldObject           old information about the tag.
     * @param    newObject           new information about the tag.
     * @return   Tag                 updated tag
     * @throws DaoException if a SQL exception occurred
     */
    Tag update(Tag oldObject, Tag newObject) throws DaoException;
}
