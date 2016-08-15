package by.epam.newsportal.service;

import by.epam.newsportal.entity.Author;
import by.epam.newsportal.entity.NewsPiece;

import java.util.List;

public interface AuthorService extends Service<Author> {

    /**
     * This method returns all authors of the news.
     *
     * @param   newsPiece       entity of NewsPiece. Authors of it will be returned.
     * @return  List<Author>    list of authors of the news. If there is no author,
     *                          the method return empty list.
     * @throws ServiceException if a DaoException occurred
     */
    List<Author> getNewsAuthors(NewsPiece newsPiece) throws ServiceException;

    /**
     * This method adds one author to news.It returns author, which was insert: if operation
     * finished without exception, the method returns the author. Otherwise it returns null,
     * or throw exception.
     *
     * @param   author       entity of Author.
     * @param   newsPiece    entity of NewsPiece.
     * @return  Author       author, which was insert
     * @throws ServiceException if a DaoException occurred
     */
    Author addAuthorToNews(Author author,NewsPiece newsPiece) throws ServiceException;

    /**
     * This method used to select the author.
     *
     * @param   author       entity of Author.
     * @return  Author       author, which was find
     * @throws ServiceException if a DaoException occurred
     */
    Author select(Author author) throws ServiceException;

    /**
     * This method used to insert new author.
     *
     * @param   author       new author
     * @return  Author       author, which was insert
     * @throws ServiceException if a SQL exception occurred
     */
    Author insert(Author author) throws ServiceException;

    /**
     * This method delete one author and returns boolean value: if operation finished without exception,
     * the method returns true. Otherwise it returns false.
     *
     * @param   author       entity of Author.
     * @return  boolean      this value means status of operation.
     * @throws ServiceException if a DaoException occurred
     */
    boolean delete(Author author) throws ServiceException;

}
