package by.epam.newsportal.service.impl;

import by.epam.newsportal.dao.*;
import by.epam.newsportal.entity.Author;
import by.epam.newsportal.entity.Comment;
import by.epam.newsportal.entity.NewsPiece;
import by.epam.newsportal.entity.Tag;
import by.epam.newsportal.service.NewsPieceService;
import by.epam.newsportal.service.ServiceException;
import by.epam.newsportal.service.search_criteria.SearchCriteria;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional(rollbackFor = ServiceException.class, propagation = Propagation.REQUIRED)
public class NewsPieceServiceImpl implements NewsPieceService {
    private CommentDao commentDao;
    private GeneralDao generalDao;
    private NewsPieceDao newsDao ;
    private AuthorDao authorDao;
    private TagDao tagDao;

    public NewsPieceServiceImpl(){}

    public NewsPieceServiceImpl(GeneralDao generalDao, NewsPieceDao newsDao,
                                AuthorDao authorDao, TagDao tagDao,CommentDao commentDao){
        this.generalDao = generalDao;
        this.newsDao  = newsDao;
        this.authorDao = authorDao;
        this.tagDao = tagDao;
        this.commentDao = commentDao;
    }

    public NewsPiece addNews(NewsPiece newsPiece, Set<Tag> tags, Set<Author> authors) throws ServiceException {
        NewsPiece addedNewsPiece = new NewsPiece();
        if(!existAuthors(authors)){
            return null;
        }
        try {
            NewsPiece insertedNewsPiece = newsDao.insert(newsPiece);
            addedNewsPiece.setNewsId(insertedNewsPiece.getNewsId());
            generalDao.addNewsAuthor(authors, addedNewsPiece);
            Set<Tag> preparedTags = getTagSet(tags);
            generalDao.addNewsTag(preparedTags,addedNewsPiece);
        } catch (DaoException e) {
            throw new ServiceException("NewsPieceServiceImpl Exception", e);
        }
        return addedNewsPiece;
    }

    public Set<Tag> getTagSet(Set<Tag> tags) throws ServiceException {
        Set<Tag> preparedTags = new HashSet<Tag>();
        for (Tag tag : tags){
            try {
                Tag preparedTag = tagDao.select(tag);
                if(preparedTag == null){
                    Tag newTag = tagDao.insert(tag);
                    preparedTags.add(newTag);
                }
                else{
                    preparedTags.add(preparedTag);
                }
            } catch (DaoException e) {
                throw new ServiceException("NewsPieceServiceImpl Exception", e);
            }
        }
        return preparedTags;
    }

    public boolean existAuthors(Set<Author> authors) throws ServiceException {
        boolean exist = true;
        for (Author author : authors){
            try {
                Author curAuthor = authorDao.select(author);
                if(curAuthor == null){
                    exist = false;
                    return exist;
                }
            } catch (DaoException e) {
                throw new ServiceException("NewsPieceServiceImpl Exception", e);
            }
        }
        return exist;
    }

    public NewsPiece edit(NewsPiece oldNewsPiece, NewsPiece newNewsPiece) throws ServiceException {
        NewsPiece editedNewsPiece = null;
        try {
            editedNewsPiece = newsDao.update(oldNewsPiece, newNewsPiece);
        } catch (DaoException e) {
            throw new ServiceException("NewsPieceServiceImpl Exception", e);
        }
        return  editedNewsPiece;
    }

    public Long getNewsCount()  throws ServiceException {
       Long count = null;
        try {
            count = newsDao.getNewsCount();
        } catch (DaoException e) {
            throw new ServiceException("NewsPieceServiceImpl Exception", e);
        }
        return count;
    }

    public List<NewsPiece> search(SearchCriteria searchCriteria) throws ServiceException {
        Author author = searchCriteria.getAuthor();
        List<Tag> tags = searchCriteria.getTags();
        try {
            if(author == null && tags == null){
                return newsDao.selectAll();
            }
            else if(author == null && !tags.isEmpty()){
                return newsDao.find(searchCriteria.getAuthor(), tags);
            }else if(author != null){
                return newsDao.find(author);
            }else if(!tags.isEmpty()){
                return newsDao.find(tags);
            }
        } catch (DaoException e) {
            throw new ServiceException("NewsPieceServiceImpl Exception", e);
        }
        return null;
    }

    public List<NewsPiece> selectAll() throws ServiceException {
        List<NewsPiece> news = null;
        try {
            news = newsDao.selectAll();
            for(NewsPiece newsPiece : news){
                List<Comment> comments = commentDao.select(newsPiece);
                Set<Tag> tags = tagDao.select(newsPiece);
                List<Author> authors = authorDao.select(newsPiece);
                Set<Author> setAuthors = new HashSet<Author>(authors);
                newsPiece.setAuthors(setAuthors);
                newsPiece.setTags(tags);
                newsPiece.setComments(comments);
            }
        } catch (DaoException e) {
            throw new ServiceException("NewsPieceServiceImpl Exception", e);
        }
        return news;
    }

    public NewsPiece select(NewsPiece news) throws ServiceException {
        NewsPiece newsPiece = null;
        try {
            newsPiece = newsDao.select(news);
        } catch (DaoException e) {
            throw new ServiceException("NewsPieceServiceImpl Exception", e);
        }
        return newsPiece;
    }

    public boolean delete(NewsPiece object) throws ServiceException {
        boolean isDelete;
        try {
            isDelete = newsDao.delete(object);
        } catch (DaoException e) {
            throw new ServiceException("NewsPieceServiceImpl Exception", e);
        }
        return isDelete;
    }

    public NewsPiece update(NewsPiece object) throws ServiceException {
        return null;
    }

    public NewsPiece insert(NewsPiece object) throws ServiceException {
        return null;
    }

}
