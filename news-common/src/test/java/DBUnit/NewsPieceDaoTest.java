package DBUnit;

import by.epam.newsportal.dao.DaoException;
import by.epam.newsportal.dao.NewsPieceDao;
import by.epam.newsportal.entity.Author;
import by.epam.newsportal.entity.NewsPiece;
import by.epam.newsportal.entity.Tag;
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
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations="classpath:dao_test.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class,
        TransactionalTestExecutionListener.class})
public class NewsPieceDaoTest {
    public static final String CLASSPATH = "classpath:news_data.xml";

    @Autowired
    NewsPieceDao newsPieceDao;

    public DataBuilder dataBuilder = new DataBuilder();

    @Test
    @DatabaseSetup(value = CLASSPATH, type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = {CLASSPATH}, type = DatabaseOperation.DELETE)
    public void testGetNewsCount() throws DaoException {
        Long count = newsPieceDao.getNewsCount();
        Long realCount = 3L;
        Assert.assertEquals(count,realCount);
    }

   @Test
    @DatabaseSetup(value = CLASSPATH, type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = {CLASSPATH}, type = DatabaseOperation.DELETE)
    public void  testFindByAuthor() throws DaoException, ParseException {
        Author author = dataBuilder.createAuthor();
        List<NewsPiece> result = newsPieceDao.find(author);
        assertThat(result).hasSize(1);
    }

    @Test
    @DatabaseSetup(value = CLASSPATH, type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = {CLASSPATH}, type = DatabaseOperation.DELETE)
    public void testFingByTags() throws DaoException {
        Set<Tag> setTags = dataBuilder.createTags();
        List<Tag> listTags = new ArrayList<Tag>(setTags);
        List<NewsPiece> result = newsPieceDao.find(listTags);
        assertThat(result).hasSize(1);
    }

    @Test
    @DatabaseSetup(value = CLASSPATH, type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = {CLASSPATH}, type = DatabaseOperation.DELETE)
    public void testSelectAll() throws DaoException {
        List<NewsPiece> result = newsPieceDao.selectAll();
        assertThat(result).hasSize(3);
    }

   @Test
    @DatabaseSetup(value = CLASSPATH, type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = {CLASSPATH}, type = DatabaseOperation.DELETE)
    public void testFindAllNews() throws DaoException, ParseException {
        Author author = dataBuilder.createAuthor();
        Set<Tag> setTags = dataBuilder.createTags();
        List<Tag> listTags = new ArrayList<Tag>(setTags);
        List<NewsPiece> newsPieceList = newsPieceDao.find(author,listTags);
        assertThat(newsPieceList).hasSize(1);
    }

    @Test
    @DatabaseSetup(value = CLASSPATH, type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = {CLASSPATH}, type = DatabaseOperation.DELETE)
    public void testUpdateNews() throws DaoException, ParseException {
        NewsPiece oldNews = dataBuilder.createNewsPiece();
        NewsPiece newNews = new NewsPiece();
        newNews.setNewsId(100L);
        newNews.setTitle("new_title");

        NewsPiece updatedNews = newsPieceDao.update(oldNews,newNews);
        Assert.assertNotNull(updatedNews);
    }

    @Test
    @DatabaseSetup(value = CLASSPATH, type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = {CLASSPATH}, type = DatabaseOperation.DELETE)
    public void testDelete() throws DaoException, ParseException {
        NewsPiece newsPiece = dataBuilder.createNewsPiece();
        boolean isDelete = newsPieceDao.delete(newsPiece);
        Assert.assertTrue(isDelete);
    }

    @Test
    @DatabaseSetup(value = CLASSPATH, type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = {CLASSPATH}, type = DatabaseOperation.DELETE)
    public void testAddNews() throws DaoException, ParseException {
        NewsPiece newsPiece = dataBuilder.createNewNewsPiece();
        NewsPiece insertedNews = newsPieceDao.insert(newsPiece);
        Assert.assertNotNull(insertedNews);
    }
}
