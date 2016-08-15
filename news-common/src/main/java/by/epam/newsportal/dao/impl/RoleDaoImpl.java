package by.epam.newsportal.dao.impl;

import by.epam.newsportal.dao.DaoException;
import by.epam.newsportal.dao.RoleDao;
import by.epam.newsportal.entity.Role;
import by.epam.newsportal.entity.User;

import java.util.List;

public class RoleDaoImpl implements RoleDao {
    private static final RoleDaoImpl instance = new RoleDaoImpl();
    private RoleDaoImpl(){}
    public static RoleDaoImpl getInstance() {
        return instance;
    }

    public List<Role> select(User entity) throws DaoException {
        return null;
    }

    public List<Role> selectAll() throws DaoException {
        return null;
    }

    public Role select(Role entity) throws DaoException {
        return null;
    }

    public Role insert(Role entity) throws DaoException {
        return null;
    }

    public boolean delete(Role entity) throws DaoException {
        return false;
    }

    public Role update(Role oldObject, Role newObject) throws DaoException {
        return null;
    }

}
