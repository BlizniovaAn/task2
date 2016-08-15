package by.epam.newsportal.service.impl;

import by.epam.newsportal.dao.DaoException;
import by.epam.newsportal.dao.UserDao;
import by.epam.newsportal.entity.User;
import by.epam.newsportal.service.ServiceException;
import by.epam.newsportal.service.UserService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = ServiceException.class, propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    public UserServiceImpl(){ }
    public UserServiceImpl(UserDao userDao){
        this.userDao = userDao;
    }

    public User insert(User user) throws ServiceException {
        User insertedUser = null;
        try {
            insertedUser = userDao.insert(user);
        } catch (DaoException e) {
            throw new ServiceException("UserServiceImpl Exception",e);
        }
        return insertedUser;
    }

    public List<User> selectAll() throws ServiceException {
        List<User> users = null;
        try {
            users = userDao.selectAll();
        } catch (DaoException e) {
            throw new ServiceException("UserServiceImpl Exception",e);
        }
        return users;
    }

    public User select(User object) throws ServiceException {
        return null;
    }

    public boolean delete(User object) throws ServiceException {
        return false;
    }

    public User update(User object) throws ServiceException {
        return null;
    }

}
