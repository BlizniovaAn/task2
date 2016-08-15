package by.epam.newsportal.service.impl;

import by.epam.newsportal.dao.CommentDao;
import by.epam.newsportal.dao.DaoException;
import by.epam.newsportal.dao.NewsPieceDao;
import by.epam.newsportal.dao.impl.CommentDaoImpl;
import by.epam.newsportal.entity.Comment;
import by.epam.newsportal.entity.NewsPiece;
import by.epam.newsportal.service.CommentService;
import by.epam.newsportal.service.ServiceException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = ServiceException.class, propagation = Propagation.REQUIRED)
public class CommentServiceImpl implements CommentService {
    private CommentDaoImpl commentDaoImpl;
    private CommentDao commentDao;
    public CommentServiceImpl(){}
    public  CommentServiceImpl(CommentDaoImpl commentDaoImpl){
        this.commentDaoImpl = commentDaoImpl;
    }
    public  CommentServiceImpl(CommentDao commentDao){
        this.commentDao = commentDao;
    }


    public List<Comment> select(NewsPiece newsPiece) throws ServiceException {
        List<Comment> comments = null;
        try {
            comments = commentDao.select(newsPiece);
        } catch (DaoException e) {
            throw new ServiceException("CommentServiceImpl Exception", e);
        }
        return comments;
    }

    public List<Comment> addCommentsToNews(NewsPiece newsPiece, List<Comment> newComments) throws ServiceException {
        List<Comment> comments = null;
        try {
            comments = commentDao.addCommentsToNews(newsPiece, newComments);
        } catch (DaoException e) {
            throw new ServiceException("CommentServiceImpl Exception", e);
        }
        return comments;
    }

    public boolean deleteNewsComments(NewsPiece newsPiece) throws ServiceException {
        boolean isDelete = false;
        try {
            isDelete = commentDao.deleteNewsComments(newsPiece);
        } catch (DaoException e) {
            throw new ServiceException("CommentServiceImpl Exception", e);
        }
        return isDelete;
    }

    public boolean delete(Comment comment) throws ServiceException {
        boolean isDelete = false;
        try {
            isDelete = commentDao.delete(comment);
        } catch (DaoException e) {
            throw new ServiceException("CommentServiceImpl Exception", e);
        }
        return isDelete;
    }

    public List<Comment> selectAll() throws ServiceException {
        return null;
    }

    public Comment select(Comment object) throws ServiceException {
        return null;
    }

    public Comment insert(Comment object) throws ServiceException {
        return null;
    }


    public Comment update(Comment object) throws ServiceException {
        return null;
    }

}
