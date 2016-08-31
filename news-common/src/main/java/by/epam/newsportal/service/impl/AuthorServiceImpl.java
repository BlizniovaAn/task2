package by.epam.newsportal.service.impl;

import by.epam.newsportal.dao.AuthorDao;
import by.epam.newsportal.dao.DaoException;
import by.epam.newsportal.entity.Author;
import by.epam.newsportal.entity.NewsPiece;
import by.epam.newsportal.service.AuthorService;
import by.epam.newsportal.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = ServiceException.class, propagation = Propagation.REQUIRED)
public class AuthorServiceImpl implements AuthorService {
    private AuthorDao authorDao;
    public AuthorServiceImpl(){}

    public AuthorServiceImpl(AuthorDao authorDao){
        this.authorDao = authorDao;
    }

    public Author select(Author object) throws ServiceException {
        Author author = null;
        try {
            author = authorDao.select(object);
        } catch (DaoException e) {
            throw new ServiceException("AuthorServiceImpl Exception", e);
        }
        return author;
    }

    public List<Author> getNewsAuthors(NewsPiece newsPiece) throws ServiceException {
        List<Author> authors = null;
        try {
            authors = authorDao.select(newsPiece);
        } catch (DaoException e) {
            throw new ServiceException("AuthorServiceImpl Exception", e);
        }
        return authors;
    }

    public Author addAuthorToNews(Author author, NewsPiece newsPiece) throws ServiceException {
        Author insertedAuthor = null;
        try {
            if(authorDao.select(author) == null){
                return insertedAuthor;
            }
            insertedAuthor = authorDao.addAuthorToNews(author, newsPiece);
        } catch (DaoException e) {
            throw new ServiceException("AuthorServiceImpl Exception", e);
        }
        return insertedAuthor;
    }

    public Author insert(Author object) throws ServiceException {
        return null;
    }

    public boolean delete(Author object) throws ServiceException {
        boolean isDelete;
        try {
            isDelete = authorDao.delete(object);
        } catch (DaoException e) {
            throw new ServiceException("AuthorServiceImpl Exception", e);
        }
        return isDelete;
    }

    public Author update(Author object) throws ServiceException {
        return null;
    }
    public List<Author> selectAll() throws ServiceException {
        List<Author> authors;
        try {
            authors = authorDao.selectAll();
        } catch (DaoException e) {
            throw new ServiceException("AuthorServiceImpl Exception", e);
        }
        return authors;
    }

}
