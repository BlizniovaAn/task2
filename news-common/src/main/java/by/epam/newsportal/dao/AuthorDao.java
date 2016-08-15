package by.epam.newsportal.dao;

import by.epam.newsportal.entity.Author;
import by.epam.newsportal.entity.NewsPiece;

import java.util.List;

public interface AuthorDao extends Dao<Author>{
    /**
     * This method returns all authors of the news.
     *
     * @param   newsPiece       entity of NewsPiece. Authors of it will be returned.
     * @return  List<Author>    list of authors of the news. If there is no author,
     *                          the method return empty list.
     * @throws DaoException if a SQL exception occurred
     */
    List<Author> select(NewsPiece newsPiece) throws DaoException;

    /**
     * This method delete one author and returns boolean value: if operation finished without exception,
     * the method returns true. Otherwise it returns false.
     *
     * @param   author       entity of Author.
     * @return  boolean      this value means status of operation.
     * @throws DaoException if a SQL exception occurred
     */
    boolean delete(Author author) throws DaoException;

    /**
     * This method adds one author to news.It returns author, which was insert: if operation finished without exception,
     * the method returns the author. Otherwise it returns null, or throw exception.
     *
     * @param   author       entity of Author.
     * @param   newsPiece    entity of NewsPiece.
     * @return  Author       author, which was insert
     * @throws DaoException if a SQL exception occurred
     */
    Author addAuthorToNews(Author author,NewsPiece newsPiece) throws DaoException;

    /**
     * This method used to select all authors from the database.
     * If the author has field EXPIRED not null,he will be absent in this list.
     *
     * @return  List<Author>      all authors
     * @throws DaoException if a SQL exception occurred
     */
    List<Author> selectAll() throws DaoException;

    /**
     * This method used to select the author.
     *
     * @param   author       entity of Author.
     * @return  Author       author, which was find
     * @throws DaoException if a SQL exception occurred
     */
    Author select(Author author) throws DaoException;

    /**
     * This method used to insert new author.
     *
     * @param   author       new author
     * @return  Author       author, which was insert
     * @throws DaoException if a SQL exception occurred
     */
    Author insert(Author author) throws DaoException;

    /**
     * This method used to update information about the author
     *
     * @param   oldObject       old information about the author
     * @param   newObject       new information about the author
     * @return  Author          author, which was edit
     * @throws DaoException if a SQL exception occurred
     */
    Author update(Author oldObject, Author newObject) throws DaoException;
}
