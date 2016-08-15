package by.epam.newsportal.service.impl;

import by.epam.newsportal.dao.RoleDao;
import by.epam.newsportal.entity.Role;
import by.epam.newsportal.service.RoleService;
import by.epam.newsportal.service.ServiceException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional(rollbackFor = ServiceException.class, propagation = Propagation.REQUIRED)
public class RoleServiceImpl implements RoleService{
    private RoleDao roleDao;

    public RoleServiceImpl(){}
    public RoleServiceImpl(RoleDao roleDao){
        this.roleDao = roleDao;
    }

    public List<Role> selectAll() throws ServiceException {
        return null;
    }

    public Role select(Role object) throws ServiceException {
        return null;
    }

    public Role insert(Role object) throws ServiceException {
        return null;
    }

    public boolean delete(Role object) throws ServiceException {
        return false;
    }

    public Role update(Role object) throws ServiceException {
        return null;
    }
}
