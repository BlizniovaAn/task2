package DBUnit;

import by.epam.newsportal.dao.CommentDao;
import by.epam.newsportal.dao.DaoException;
import by.epam.newsportal.entity.Comment;
import by.epam.newsportal.entity.NewsPiece;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;

import data_builder.DataBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations="classpath:dao_test.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class,
        TransactionalTestExecutionListener.class})

public class CommentDaoTest {
    public static final String CLASSPATH = "classpath:news_data.xml";

    @Autowired
    CommentDao commentDao;

    public DataBuilder dataBuilder = new DataBuilder();

    @Test
    @DatabaseSetup(value = CLASSPATH, type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = {CLASSPATH}, type = DatabaseOperation.DELETE)
    public void testGetNewsComments() throws ParseException, DaoException {
        NewsPiece newsPiece = dataBuilder.createNewsPiece();
        List<Comment> searchResults = commentDao.select(newsPiece);
        assertThat(searchResults).hasSize(3);
    }

    @Test(expected = NullPointerException.class)
    @DatabaseSetup(value = CLASSPATH, type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = {CLASSPATH}, type = DatabaseOperation.DELETE)
    public void testGetNewsCommentsExpNullPointerException() throws ParseException, DaoException {
        NewsPiece newsPiece = null;
        List<Comment> searchResults = commentDao.select(newsPiece);
        assertThat(searchResults).hasSize(3);
    }

    @Test
    @DatabaseSetup(value = CLASSPATH, type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = {CLASSPATH}, type = DatabaseOperation.DELETE)
    public void testGetNewsCommentsExpNull() throws ParseException, DaoException {
        NewsPiece newsPiece = dataBuilder.createNewNewsPiece();
        List<Comment> searchResults = commentDao.select(newsPiece);
        assertThat(searchResults).hasSize(0);
    }

    @Test
    @DatabaseSetup(value = CLASSPATH, type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = {CLASSPATH}, type = DatabaseOperation.DELETE)
    public void testAddCommentsToNews() throws ParseException, DaoException {
        NewsPiece newsPiece = dataBuilder.createNewsPiece();
        List<Comment> comments = dataBuilder.createComments();
        List<Comment> result = commentDao.addCommentsToNews(newsPiece, comments);
        Assert.assertEquals(comments.size(), result.size());
    }

    @Test(expected = NullPointerException.class)
    @DatabaseSetup(value = CLASSPATH, type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = {CLASSPATH}, type = DatabaseOperation.DELETE)
    public void testAddCommentsToNewsExpNullPointerException() throws ParseException, DaoException {
        NewsPiece newsPiece = null;
        List<Comment> comments = null;
        List<Comment> result = commentDao.addCommentsToNews(newsPiece, comments);
        Assert.assertEquals(comments.size(), result.size());
    }

    @Test
    @DatabaseSetup(value = CLASSPATH, type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = {CLASSPATH}, type = DatabaseOperation.DELETE)
    public void testDeleteComments() throws ParseException, DaoException {
        NewsPiece newsPiece = dataBuilder.createNewsPiece();
        Assert.assertTrue(commentDao.deleteNewsComments(newsPiece));
    }

    @Test(expected = NullPointerException.class)
    @DatabaseSetup(value = CLASSPATH, type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = {CLASSPATH}, type = DatabaseOperation.DELETE)
    public void testDeleteCommentsExpNullPointerException() throws ParseException, DaoException {
        NewsPiece newsPiece = null;
        Assert.assertTrue(commentDao.deleteNewsComments(newsPiece));
    }

    @Test
    @DatabaseSetup(value = CLASSPATH, type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = {CLASSPATH}, type = DatabaseOperation.DELETE)
    public void testDeleteComment() throws ParseException, DaoException {
        Comment comment = dataBuilder.createComment();
        Assert.assertTrue(commentDao.delete(comment));
    }

    @Test
    @DatabaseSetup(value = CLASSPATH, type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = {CLASSPATH}, type = DatabaseOperation.DELETE)
    public void testDeleteCommentExpDaoException() throws ParseException, DaoException {
        Comment comment = dataBuilder.createNewComment();
        Assert.assertFalse(commentDao.delete(comment));
    }
}
