package by.epam.newsportal.dao;

import by.epam.newsportal.entity.Author;
import by.epam.newsportal.entity.NewsPiece;
import by.epam.newsportal.entity.Tag;

import java.util.List;
import java.util.Set;

public interface GeneralDao extends Dao<Author>{
    /**
     * This method used to add authors to news. If one of parameters are null,
     * the method throw NullPointerException.
     *
     * @param    authors           authors, which will be added to news.
     * @param    newsPiece         authors will be added to this news.
     * @return   List<Author>      added authors
     * @throws DaoException if a SQL exception occurred
     */
    List<Author> addNewsAuthor(Set<Author> authors, NewsPiece newsPiece) throws DaoException;

    /**
     * This method used to add tags to news.If one of parameters are null,
     * the method throw NullPointerException.
     *
     * @param    tags              tags, which will be added to news.
     * @param    newsPiece         tags will be added to this news.
     * @return   List<Tag>         added tags
     * @throws DaoException if a SQL exception occurred
     */
    List<Tag> addNewsTag(Set<Tag> tags, NewsPiece newsPiece) throws DaoException;

}
