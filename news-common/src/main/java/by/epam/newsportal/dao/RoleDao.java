package by.epam.newsportal.dao;

import by.epam.newsportal.entity.Role;
import by.epam.newsportal.entity.User;

import java.util.List;

public interface RoleDao extends Dao<Role>{
    /**
     * This method used to select a role of the user.If the parameter is
     * null,the method throw NullPointerException.
     *
     * @param    user              one role of the user. It will be found and returned
     * @return   Role              the found role
     * @throws DaoException if a SQL exception occurred
     */
    List<Role> select(User user) throws DaoException;

    /**
     * This method used to select all roles from the database.
     *
     * @return   List<Role>        list of roles. If there is no roles,
     *                             the method return empty list.
     * @throws DaoException if a SQL exception occurred
     */
    List<Role> selectAll() throws DaoException;

    /**
     * This method used to select one role from the database.If one of
     * parameters are null,the method throw NullPointerException.
     *
     * @param    role              one role. It will be found and returned
     * @return   Role              the found role
     * @throws DaoException if a SQL exception occurred
     */
    Role select(Role role) throws DaoException;

    /**
     * This method used to add one role to the database.If the parameter is
     * null,the method throw NullPointerException.
     *
     * @param    role              one role. It will be added and returned
     * @return   NewsPiece         the added role
     * @throws DaoException if a SQL exception occurred
     */
    Role insert(Role role) throws DaoException;

    /**
     * This method used to delete one role from the database and returns boolean value:
     * if operation finished without exception, the method returns true. Otherwise it returns false.
     * If the parameter @param role is null,the method throw NullPointerException.
     *
     * @param    role              one role. It will be removed
     * @return   boolean           this value means status of operation.
     * @throws DaoException if a SQL exception occurred
     */
    boolean delete(Role role) throws DaoException;

    /**
     * This method used to edit one role.
     *
     * @param    oldObject           old information about the role.
     * @param    newObject           new information about the role.
     * @return   Comment             updated comment
     * @throws DaoException if a SQL exception occurred
     */
    Role update(Role oldObject, Role newObject) throws DaoException;
}
