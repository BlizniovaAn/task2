package DBUnit;

import by.epam.newsportal.dao.DaoException;
import by.epam.newsportal.dao.GeneralDao;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations="classpath:dao_test.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class,
        TransactionalTestExecutionListener.class})
public class GeneralDaoTest {
    public static final String CLASSPATH = "classpath:news_data.xml";

    @Autowired
    GeneralDao generalDao;

    public DataBuilder dataBuilder = new DataBuilder();

    @Test
    @DatabaseSetup(value = CLASSPATH, type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = {CLASSPATH}, type = DatabaseOperation.DELETE)
    public void testAddNewsAuthor() throws ParseException, DaoException {
        Set<Author> authors = dataBuilder.createAuthors();
        NewsPiece newsPiece = dataBuilder.createNewsPiece();
        List<Author> result = generalDao.addNewsAuthor(authors,newsPiece);
        Assert.assertEquals(authors.size(), result.size());
    }

    @Test
    @DatabaseSetup(value = CLASSPATH, type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = {CLASSPATH}, type = DatabaseOperation.DELETE)
    public void testAddNewsTag() throws ParseException, DaoException {
        NewsPiece newsPiece = dataBuilder.createNewsPiece();
        Set<Tag> tags = dataBuilder.createTags();
        List<Tag> result = generalDao.addNewsTag(tags,newsPiece);
        Assert.assertEquals(tags.size(), result.size());
    }
}
