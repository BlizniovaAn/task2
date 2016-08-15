package Mockito;

import by.epam.newsportal.dao.CommentDao;
import by.epam.newsportal.dao.DaoException;
import by.epam.newsportal.entity.Comment;
import by.epam.newsportal.entity.NewsPiece;
import by.epam.newsportal.service.ServiceException;
import by.epam.newsportal.service.impl.CommentServiceImpl;
import data_builder.DataBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:dao_test.xml"})
public class CommentServiceTest {
    @Mock
    private CommentDao commentDao;

    private CommentServiceImpl commentService;

    public DataBuilder dataBuilder = new DataBuilder();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        commentService = new CommentServiceImpl(commentDao);
    }

    @Test
    public void testSelectNewsComments() throws ParseException, ServiceException, DaoException {
        when(commentDao.select(any(NewsPiece.class))).thenReturn(new LinkedList<Comment>());
        NewsPiece newsPiece = dataBuilder.createNewsPiece();
        List<Comment> commentList = commentService.select(newsPiece);
        Assert.assertNotNull(commentList);
    }

    @Test(expected = ServiceException.class)
    public void testSelectNewsCommentsExpServiceException() throws ParseException, ServiceException, DaoException {
        when(commentDao.select(any(NewsPiece.class))).thenThrow(new DaoException());
        NewsPiece newsPiece = dataBuilder.createNewNewsPiece();
        commentService.select(newsPiece);
    }

   @Test
    public void testAddCommentsToNews() throws ServiceException, DaoException, ParseException {
        when(commentDao.addCommentsToNews(any(NewsPiece.class), any(List.class))).thenReturn(new ArrayList<Comment>());
        List<Comment> commentList = commentService.addCommentsToNews(any(NewsPiece.class), any(List.class));
        Assert.assertNotNull(commentList);
   }

    @Test(expected = ServiceException.class)
    public void testAddCommentsToNewsExpServiceException() throws ServiceException, DaoException, ParseException {
        NewsPiece newsPiece = dataBuilder.createNewNewsPiece();
        List<Comment> comments = new ArrayList<Comment>();
        when(commentDao.addCommentsToNews(newsPiece, comments)).thenThrow(new DaoException());
        commentService.addCommentsToNews(newsPiece, comments);
    }

    @Test
    public void testDeleteNewsComments() throws ServiceException, DaoException {
        when(commentDao.deleteNewsComments(any(NewsPiece.class))).thenReturn(true);
        boolean result = commentService.deleteNewsComments(any(NewsPiece.class));
        Assert.assertTrue(result);
    }

    @Test(expected = ServiceException.class)
    public void testDeleteNewsCommentsExpServiceException() throws ServiceException, DaoException {
        NewsPiece newsPiece = dataBuilder.createNewNewsPiece();
        when(commentDao.deleteNewsComments(newsPiece)).thenThrow(new DaoException());
        commentService.deleteNewsComments(newsPiece);
    }

    @Test
    public void testDeleteComment() throws ServiceException, DaoException {
        when(commentDao.delete(any(Comment.class))).thenReturn(true);
        boolean result = commentService.delete(any(Comment.class));
        Assert.assertTrue(result);
    }

    @Test(expected = ServiceException.class)
    public void testDeleteCommentExpServiceException() throws ServiceException, DaoException {
        Comment comment = dataBuilder.createNewComment();
        when(commentDao.delete(comment)).thenThrow(new DaoException());
        commentService.delete(comment);
    }

    @Test(expected = NullPointerException.class)
    public void testDeleteCommentExpNullPointerException() throws ServiceException, DaoException {
        Comment comment = null;
        when(commentDao.delete(comment)).thenThrow(new NullPointerException());
        commentService.delete(comment);
    }
}
