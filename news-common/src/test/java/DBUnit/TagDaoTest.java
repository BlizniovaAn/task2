package DBUnit;

import by.epam.newsportal.dao.DaoException;
import by.epam.newsportal.dao.TagDao;
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

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations="classpath:dao_test.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class,
        TransactionalTestExecutionListener.class})

public class TagDaoTest {
    public static final String CLASSPATH = "classpath:news_data.xml";

    @Autowired
    public TagDao tagDao;

    public DataBuilder dataBuilder = new DataBuilder();

    @Test
    @DatabaseSetup(value = CLASSPATH, type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = {CLASSPATH}, type = DatabaseOperation.DELETE)
    public void testSelect() throws DaoException {
        NewsPiece newsPiece = dataBuilder.createNewsPiece();
        Set<Tag> searchResults = tagDao.select(newsPiece);
        assertThat(searchResults).hasSize(2);
    }

    @Test
    @DatabaseSetup(value = CLASSPATH, type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = {CLASSPATH}, type = DatabaseOperation.DELETE)
    public void testAddTagToNews() throws DaoException {
        NewsPiece newsPiece = dataBuilder.createNewsPiece();
        Tag tag = dataBuilder.createTag();
        Tag result = tagDao.addTagToNews(newsPiece, tag);
        Assert.assertNotNull(result);
    }

    @Test
    @DatabaseSetup(value = CLASSPATH, type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = {CLASSPATH}, type = DatabaseOperation.DELETE)
    public void testInsert() throws DaoException {
        Tag tag = dataBuilder.createNewTag();
        Tag result = tagDao.insert(tag);
        Assert.assertNotNull(result);
    }
}
