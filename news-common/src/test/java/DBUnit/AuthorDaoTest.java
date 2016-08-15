package DBUnit;

import by.epam.newsportal.dao.AuthorDao;
import by.epam.newsportal.dao.DaoException;
import by.epam.newsportal.entity.Author;
import by.epam.newsportal.entity.NewsPiece;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import data_builder.DataBuilder;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations="classpath:dao_test.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class,
        TransactionalTestExecutionListener.class})

public class AuthorDaoTest {
    public static final String CLASSPATH = "classpath:news_data.xml";

    @Autowired
    public AuthorDao authorDao;

    public DataBuilder dataBuilder = new DataBuilder();

    @Test
    @DatabaseSetup(value = CLASSPATH, type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = {CLASSPATH}, type = DatabaseOperation.DELETE)
    public void testGetAll() throws DaoException {
        List<Author> searchResults = authorDao.selectAll();
        assertThat(searchResults).hasSize(2);
    }

   @Test
    @DatabaseSetup(value = CLASSPATH, type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = {CLASSPATH}, type = DatabaseOperation.DELETE)
    public void testSelectOne() throws DaoException, ParseException {
        Author author = dataBuilder.createAuthor();
        Author selectedAuthor = authorDao.select(author);
        Assert.assertEquals(author.getAuthorId(), selectedAuthor.getAuthorId());
    }


    @Test(expected = NullPointerException.class)
    @DatabaseSetup(value = CLASSPATH, type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = {CLASSPATH}, type = DatabaseOperation.DELETE)
    public void testSelectOneExpNullPointerException() throws DaoException, ParseException {
        Author author = null;
        Author selectedAuthor = authorDao.select(author);
        Assert.assertEquals(author.getAuthorId(), selectedAuthor.getAuthorId());
    }

    @Test
    @DatabaseSetup(value = CLASSPATH, type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = {CLASSPATH}, type = DatabaseOperation.DELETE)
     public void selectNewsAuthors() throws DaoException, ParseException {
         NewsPiece newsPiece = dataBuilder.createNewsPiece();
         List<Author> searchResults = authorDao.select(newsPiece);
         assertThat(searchResults).hasSize(1);
     }

    @Test
    @DatabaseSetup(value = CLASSPATH, type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = {CLASSPATH}, type = DatabaseOperation.DELETE)
    public void addAuthorToNews() throws ParseException, DaoException {
        NewsPiece newsPiece = dataBuilder.createNewsPiece();
        Author newAuthor = dataBuilder.createAuthor();
        Author author = authorDao.addAuthorToNews(newAuthor, newsPiece);
        Assert.assertEquals(author.getAuthorId(), newAuthor.getAuthorId());
    }

    @Test(expected = NullPointerException.class)
    @DatabaseSetup(value = CLASSPATH, type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = {CLASSPATH}, type = DatabaseOperation.DELETE)
    public void addAuthorToNewsExpNullPointerException() throws ParseException, DaoException {
        NewsPiece newsPiece = null;
        Author newAuthor = dataBuilder.createAuthor();
        Author author = authorDao.addAuthorToNews(newAuthor, newsPiece);
        Assert.assertEquals(author.getAuthorId(), newAuthor.getAuthorId());
    }

    @Test(expected = DaoException.class)
    @DatabaseSetup(value = CLASSPATH, type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = {CLASSPATH}, type = DatabaseOperation.DELETE)
    public void addAuthorToNewsExpDaoException() throws ParseException, DaoException {
        NewsPiece newsPiece = dataBuilder.createNewsPiece();
        Author newAuthor = dataBuilder.createNewAuthor();
        Author author = authorDao.addAuthorToNews(newAuthor, newsPiece);
        Assert.assertEquals(author.getAuthorId(), newAuthor.getAuthorId());
    }

    @Test(expected = DaoException.class)
    @DatabaseSetup(value = CLASSPATH, type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = {CLASSPATH}, type = DatabaseOperation.DELETE)
    public void testAddAuthorToNewsExpDaoException() throws ParseException, DaoException {
        NewsPiece newsPiece = dataBuilder.createNewNewsPiece();
        Author newAuthor = dataBuilder.createNewAuthor();
        Author author = authorDao.addAuthorToNews(newAuthor, newsPiece);
        Assert.assertEquals(author.getAuthorId(), newAuthor.getAuthorId());
    }

    @Test(expected = NullPointerException.class)
    @DatabaseSetup(value = CLASSPATH, type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = {CLASSPATH}, type = DatabaseOperation.DELETE)
    public void testAddAuthorToNewsExpNullPointerException() throws ParseException, DaoException {
        NewsPiece newsPiece = null;
        Author newAuthor = null;
        Author author = authorDao.addAuthorToNews(newAuthor, newsPiece);
        Assert.assertEquals(author.getAuthorId(), newAuthor.getAuthorId());
    }

    @Test
    @DatabaseSetup(value = CLASSPATH, type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = {CLASSPATH}, type = DatabaseOperation.DELETE)
    public void testDeleteAuthor() throws ParseException, DaoException {
        Author author = dataBuilder.createAuthor();
        boolean isDelete = authorDao.delete(author);
        Assert.assertTrue(isDelete);
    }

    @Test(expected = NullPointerException.class)
    @DatabaseSetup(value = CLASSPATH, type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = {CLASSPATH}, type = DatabaseOperation.DELETE)
    public void testDeleteAuthorExpNullPointerException() throws ParseException, DaoException {
        Author author = null;
        boolean isDelete = authorDao.delete(author);
        Assert.assertTrue(isDelete);
    }
}
