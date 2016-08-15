package Mockito;

import by.epam.newsportal.dao.DaoException;
import by.epam.newsportal.dao.TagDao;
import by.epam.newsportal.entity.NewsPiece;
import by.epam.newsportal.entity.Tag;
import by.epam.newsportal.service.ServiceException;
import by.epam.newsportal.service.impl.TagServiceImpl;
import data_builder.DataBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:dao_test.xml" })
public class TagServiceTest {
    @Mock
    private TagDao tagDao;

    private TagServiceImpl tagService;

    public DataBuilder dataBuilder = new DataBuilder();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        tagService = new TagServiceImpl(tagDao);
    }

    @Test
    public void testSelect() throws DaoException, ServiceException {
        Set<Tag> tags = dataBuilder.createTags();
        NewsPiece newsPiece = dataBuilder.createNewsPiece();
        when(tagDao.select(newsPiece)).thenReturn(tags);
        Set<Tag> result = tagService.select(newsPiece);
        Assert.assertEquals(result,tags);
    }

    @Test
    public void testAddTagsToNews() throws DaoException, ServiceException {
        Set<Tag> tags = dataBuilder.createTags();
        NewsPiece newsPiece = dataBuilder.createNewsPiece();

        when(tagDao.insert(any(Tag.class))).thenReturn(new Tag());
        when(tagDao.addTagToNews(any(NewsPiece.class),any(Tag.class))).thenReturn(new Tag());
        Set<Tag> result = tagService.addTagsToNews(tags,newsPiece);
        Assert.assertNotNull(result);
    }

}
