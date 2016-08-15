package by.epam.newsportal.service.impl;

import by.epam.newsportal.dao.DaoException;
import by.epam.newsportal.dao.TagDao;
import by.epam.newsportal.entity.NewsPiece;
import by.epam.newsportal.entity.Tag;
import by.epam.newsportal.service.ServiceException;
import by.epam.newsportal.service.TagService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional(rollbackFor = ServiceException.class, propagation = Propagation.REQUIRED)
public class TagServiceImpl implements TagService{
    private TagDao tagDao;

    public TagServiceImpl(){}

    public TagServiceImpl(TagDao tagDao){
        this.tagDao = tagDao;
    }

    public Set<Tag> select(NewsPiece newsPiece) throws ServiceException {
        Set<Tag> tags = null;
        try {
            tags = tagDao.select(newsPiece);
        } catch (DaoException e) {
            throw new ServiceException("TagServiceImpl Exception",e);
        }
        return tags;
    }

    public Set<Tag> addTagsToNews(Set<Tag> tags,NewsPiece newsPiece) throws ServiceException {
        List<Tag> listInsertedTags = new ArrayList<Tag>();
        Set<Tag> setInsertedTags = null;
        List<Tag> listUsersTags = getNewTags(tags);
        try {
            for (int i = 0; i < listUsersTags.size(); i++){
                Tag insertedTag = null;
                insertedTag = tagDao.addTagToNews(newsPiece,listUsersTags.get(i));
                listInsertedTags.add(insertedTag);
            }
            if(!listInsertedTags.isEmpty()){
                setInsertedTags = new HashSet<Tag>(listInsertedTags);
            }
        } catch (DaoException e) {
            throw new ServiceException("TagServiceImpl Exception",e);
        }
        return setInsertedTags;
    }

    public List<Tag> getNewTags(Set<Tag> tags) throws ServiceException {
        List<Tag> listTags = new ArrayList<Tag>(tags);
        List<Tag> newListTags = new ArrayList<Tag>();
        try {
            for(Tag tag : listTags){
                boolean isExist = existTag(tag);
                if(!isExist){
                    Tag newTag = tagDao.insert(tag);
                    newListTags.add(newTag);
                }
                else{
                    newListTags.add(tag);
                }
            }
        } catch (DaoException e) {
            throw new ServiceException("TagServiceImpl Exception",e);
        }
        return newListTags;
    }

    public boolean existTag(Tag tag) throws ServiceException {
        boolean isExist = true;
        Tag checkedTag = new Tag();
        try {
            checkedTag = tagDao.select(tag);
            if(checkedTag == null){
                isExist = false;
            }
        } catch (DaoException e) {
            throw new ServiceException("TagServiceImpl Exception",e);
        }
        return isExist;
    }

    public Tag select(Tag object) throws ServiceException {
        Tag tag = null;
        try {
            tag = tagDao.select(object);
        } catch (DaoException e) {
            throw new ServiceException("TagServiceImpl Exception",e);
        }
        return tag;
    }

    public Tag insert(Tag object) throws ServiceException {
        Tag insertedTag = null;
        try {
            insertedTag = tagDao.insert(object);
        } catch (DaoException e) {
            throw new ServiceException("TagServiceImpl Exception",e);
        }
        return insertedTag;
    }

    public boolean delete(Tag object) throws ServiceException {
        return false;
    }

    public Tag update(Tag object) throws ServiceException {
        return null;
    }

    public List<Tag> selectAll() throws ServiceException {
        return null;
    }

}
