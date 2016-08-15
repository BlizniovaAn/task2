package Mockito;
import by.epam.newsportal.dao.DaoException;
import by.epam.newsportal.entity.Author;
import by.epam.newsportal.entity.NewsPiece;
import by.epam.newsportal.service.ServiceException;
import by.epam.newsportal.service.impl.AuthorServiceImpl;
import data_builder.DataBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import by.epam.newsportal.dao.AuthorDao;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:dao_test.xml" })
public class AuthorServiceTest {
    @Mock
    private AuthorDao authorDao;

    private AuthorServiceImpl authorService;

    public DataBuilder dataBuilder = new DataBuilder();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        authorService = new AuthorServiceImpl(authorDao);
    }

    @Test
    public void testSelect() throws ParseException, ServiceException, DaoException {
        Author author = dataBuilder.createAuthor();
        when(authorDao.select(author)).thenReturn(author);
        Author resultAuthor = authorService.select(author);
        Assert.assertNotNull(resultAuthor);
    }

   @Test
    public void testGetNewsAuthors() throws ServiceException, ParseException, DaoException {
        NewsPiece newsPiece = dataBuilder.createNewsPiece();
        when(authorDao.select(any(NewsPiece.class))).thenReturn(new ArrayList<Author>());
        List<Author> authors = authorService.getNewsAuthors(newsPiece);
        Assert.assertNotNull(authors);
    }

    @Test
    public void testAddAuthorToNews() throws ParseException, ServiceException, DaoException {
        NewsPiece newsPiece = dataBuilder.createNewsPiece();
        Author author = dataBuilder.createAuthor();
        when(authorDao.select(author)).thenReturn(author);
        when(authorDao.addAuthorToNews(author,newsPiece)).thenReturn(author);
        Author result = authorService.addAuthorToNews(author, newsPiece);
        Assert.assertNotNull(result);
    }

}
