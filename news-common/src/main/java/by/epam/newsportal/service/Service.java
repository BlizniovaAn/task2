package by.epam.newsportal.service;

import java.util.List;

public interface Service<T> {
    List<T> selectAll() throws ServiceException;

    T select(T object) throws ServiceException;

    T insert(T object) throws ServiceException;

    boolean delete(T object) throws ServiceException;

    T update(T object) throws ServiceException;
}
