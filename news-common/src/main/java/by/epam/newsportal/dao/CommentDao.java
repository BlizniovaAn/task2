package by.epam.newsportal.dao;

import by.epam.newsportal.entity.Comment;
import by.epam.newsportal.entity.NewsPiece;

import java.util.List;

public interface CommentDao extends Dao<Comment> {
    /**
     * This method returns all comments. Comments of a news are printed according to addition time.
     *
     * @param   newsPiece         entity of NewsPiece. Comments of it will be returned.
     * @return   List<Comment>    list of comments of the news. If there is no comments,
     *                            the method return empty list.
     * @throws DaoException if a SQL exception occurred
     */
    List<Comment> select(NewsPiece newsPiece) throws DaoException;

    /**
     * This method used to add comments to news.
     *
     * @param   newsPiece         entity of NewsPiece. Comments of it will be added.
     * @param   comments          comments,which will be added to news
     * @return   List<Comment>    list of comments of the news. If there is no comments,
     *                            the method return empty list.
     * @throws DaoException if a SQL exception occurred
     */
    List<Comment> addCommentsToNews(NewsPiece newsPiece,List<Comment> comments) throws DaoException;

    /**
     * This method used to delete all comments of one news.
     *
     * @param    newsPiece         entity of NewsPiece. Comments of it will be deleted.
     * @return   List<Comment>     list of comments of the news. If there is no comments,
     *                             the method return empty list.
     * @throws DaoException if a SQL exception occurred
     */
    boolean deleteNewsComments(NewsPiece newsPiece) throws DaoException;

    /**
     * This method used to select all comments from the database.
     *
     * @return   List<Comment>     list of comments of the news. If there is no comments,
     *                             the method return empty list.
     * @throws DaoException if a SQL exception occurred
     */
    List<Comment> selectAll() throws DaoException;

    /**
     * This method used to select one comment from the database.
     *
     * @param    comment           one comment. It will be found and returned
     * @return   Comment           the found comment
     * @throws DaoException if a SQL exception occurred
     */
    Comment select(Comment comment) throws DaoException;

    /**
     * This method used to add one comment to the database.
     *
     * @param    comment           one comment. It will be added and returned
     * @return   Comment           the added comment
     * @throws DaoException if a SQL exception occurred
     */
    Comment insert(Comment comment) throws DaoException;

    /**
     * This method used to delete one comment from the database and returns boolean value:
     * if operation finished without exception, the method returns true. Otherwise it returns false.
     *
     * @param    comment           one comment. It will be removed
     * @return   boolean           this value means status of operation.
     * @throws DaoException if a SQL exception occurred
     */
    boolean delete(Comment comment) throws DaoException;

    /**
     * This method used to edit one comment.
     *
     * @param    oldObject           old information about the comment.
     * @param    newObject           new information about the comment.
     * @return   Comment             updated comment
     * @throws DaoException if a SQL exception occurred
     */
    Comment update(Comment oldObject, Comment newObject) throws DaoException;
}
