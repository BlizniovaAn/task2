package by.epam.newsportal.service;

import by.epam.newsportal.entity.NewsPiece;
import by.epam.newsportal.entity.Tag;

import java.util.Set;

public interface TagService extends Service<Tag>  {

    Tag select(Tag object) throws ServiceException;

    Tag insert(Tag object) throws ServiceException;

    Set<Tag> select(NewsPiece newsPiece)  throws ServiceException;

    Set<Tag> addTagsToNews(Set<Tag> tags,NewsPiece newsPiece) throws ServiceException;

}
