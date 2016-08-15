package by.epam.newsportal.service;

import by.epam.newsportal.entity.Comment;
import by.epam.newsportal.entity.NewsPiece;

import java.util.List;

public interface CommentService extends Service<Comment> {
    /**
     * This method returns all comments. Comments of a news are printed according to addition time.
     *
     * @param   newsPiece         entity of NewsPiece. Comments of it will be returned.
     * @return   List<Comment>    list of comments of the news. If there is no comments,
     *                            the method return empty list.
     * @throws ServiceException if a DaoException occurred
     */
    List<Comment> select(NewsPiece newsPiece)  throws ServiceException;

    /**
     * This method used to add comments to news.
     *
     * @param   newsPiece         entity of NewsPiece. Comments of it will be added.
     * @param   comments          comments,which will be added to news
     * @return   List<Comment>    list of comments of the news. If there is no comments,
     *                            the method return empty list.
     * @throws ServiceException if a DaoException occurred
     */
    List<Comment> addCommentsToNews(NewsPiece newsPiece,List<Comment> comments) throws ServiceException;

    /**
     * This method used to delete all comments of one news.
     *
     * @param    newsPiece         entity of NewsPiece. Comments of it will be deleted.
     * @return   List<Comment>     list of comments of the news. If there is no comments,
     *                             the method return empty list.
     * @throws ServiceException if a DaoException occurred
     */
    boolean deleteNewsComments(NewsPiece newsPiece) throws ServiceException;

    /**
     * This method used to delete one comment from the database and returns boolean value:
     * if operation finished without exception, the method returns true. Otherwise it returns false.
     *
     * @param    comment           one comment. It will be removed
     * @return   boolean           this value means status of operation.
     * @throws ServiceException if a DaoException occurred
     */
    boolean delete(Comment comment) throws ServiceException;

}
