package by.epam.newsportal.service;

import by.epam.newsportal.entity.User;

import java.util.List;

public interface UserService extends Service<User> {

    List<User> selectAll() throws ServiceException;

    User insert(User object) throws ServiceException;
}
