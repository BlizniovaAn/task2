package Mockito;

import by.epam.newsportal.dao.*;
import by.epam.newsportal.entity.Author;
import by.epam.newsportal.entity.NewsPiece;
import by.epam.newsportal.entity.Tag;
import by.epam.newsportal.service.ServiceException;
import by.epam.newsportal.service.impl.NewsPieceServiceImpl;
import by.epam.newsportal.service.search_criteria.SearchCriteria;
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
import java.util.List;
import java.util.Set;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anySet;
import static org.mockito.Mockito.when;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:dao_test.xml"})
public class NewsPieceServiceTest {
    @Mock
    private NewsPieceDao newsPieceDao;
    @Mock
    private CommentDao commentDao;
    @Mock
    private GeneralDao generalDao;
    @Mock
    private AuthorDao authorDao;
    @Mock
    private TagDao tagDao;


    private NewsPieceServiceImpl newsPieceService;

    public DataBuilder dataBuilder = new DataBuilder();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        newsPieceService = new NewsPieceServiceImpl(generalDao,newsPieceDao,authorDao,tagDao,commentDao);
    }

    @Test
    public void testExistsAuthors() throws DaoException, ParseException, ServiceException {
        Set<Author> authorSet = dataBuilder.createAuthors();
        for (Author author : authorSet){
            when(authorDao.select(author)).thenReturn(author);
        }
        boolean result = newsPieceService.existAuthors(authorSet);
        Assert.assertTrue(result);
    }

    @Test
    public void testAddNews() throws ParseException, DaoException, ServiceException {
        Set<Tag> tags = dataBuilder.createTags();
        NewsPiece newsPiece = dataBuilder.createNewsPiece();

        Set<Author> authorSet = dataBuilder.createAuthors();
        for (Author author : authorSet){
            when(authorDao.select(author)).thenReturn(author);
        }
        when(newsPieceDao.insert(newsPiece)).thenReturn(newsPiece);
        when(generalDao.addNewsAuthor(authorSet, newsPiece)).thenReturn(new ArrayList<Author>());
        when(tagDao.select(any(Tag.class))).thenReturn(any(Tag.class));
        when(generalDao.addNewsTag(anySet(),newsPiece)).thenReturn(new ArrayList<Tag>());

        NewsPiece result = newsPieceService.addNews(newsPiece, tags, authorSet);

        Assert.assertNotNull(result);
    }

    @Test
    public void testEditNews() throws DaoException, ServiceException {
        when(newsPieceDao.update(any(NewsPiece.class), any(NewsPiece.class))).thenReturn(new NewsPiece());
        NewsPiece newsPiece = newsPieceService.edit(any(NewsPiece.class), any(NewsPiece.class));
        Assert.assertNotNull(newsPiece);
    }

    @Test(expected = ServiceException.class)
    public void testEditNonexistentNews() throws DaoException, ServiceException {
        NewsPiece newNewsPiece = dataBuilder.createNewNewsPiece();
        NewsPiece newsPiece = dataBuilder.createNewsPiece();
        when(newsPieceDao.update(newNewsPiece, newsPiece)).thenThrow(new DaoException());
        newsPieceService.edit(newNewsPiece, newsPiece);
    }

    @Test
    public void testGetNewsCount() throws DaoException, ServiceException {
        Long count = 10L;
        when(newsPieceDao.getNewsCount()).thenReturn(count);
        Long result = newsPieceService.getNewsCount();
        Assert.assertEquals(result, count);
    }
    @Test
    public void testDelete() throws ServiceException, DaoException {
        when(newsPieceDao.delete(any(NewsPiece.class))).thenReturn(true);
        boolean result = newsPieceService.delete(any(NewsPiece.class));
        Assert.assertTrue(result);
    }

    @Test(expected = ServiceException.class)
    public void testDeleteNonexistentObject() throws ServiceException, DaoException {
        NewsPiece newsPiece = dataBuilder.createNewNewsPiece();
        when(newsPieceDao.delete(newsPiece)).thenThrow(new DaoException());
        newsPieceService.delete(newsPiece);
    }

    @Test(expected = NullPointerException.class)
    public void testDeleteExpNull() throws ServiceException, DaoException {
        NewsPiece newsPiece = null;
        when(newsPieceDao.delete(newsPiece)).thenThrow(new NullPointerException());
        newsPieceService.delete(newsPiece);
    }

    @Test
    public void testSearch() throws ServiceException, DaoException {
        SearchCriteria searchCriteria = new SearchCriteria();
        when(newsPieceDao.find(any(Author.class))).thenReturn(new ArrayList<NewsPiece>());
        List<NewsPiece> result = newsPieceService.search(searchCriteria);
        Assert.assertNotNull(result);
    }

    @Test(expected = NullPointerException.class)
    public void testSearchExpNullPointerException() throws ServiceException, DaoException {
        SearchCriteria searchCriteria = null;
        when(newsPieceDao.find(any(Author.class))).thenReturn(new ArrayList<NewsPiece>());
        newsPieceService.search(searchCriteria);
    }
}
