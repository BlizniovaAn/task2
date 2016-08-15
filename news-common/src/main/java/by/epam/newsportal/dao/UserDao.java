package by.epam.newsportal.dao;

import by.epam.newsportal.entity.User;

import java.util.List;

public interface UserDao extends Dao<User> {
    /**
     * This method used to select all users from the database.
     *
     * @return   List<User>        list of users. If there is no users,
     *                             the method return empty list.
     * @throws DaoException if a SQL exception occurred
     */
    List<User> selectAll() throws DaoException;

    /**
     * This method used to select one user from the database.If the parameter is
     * null,the method throw NullPointerException.
     *
     * @param    user              one user. It will be found and returned
     * @return   User              the found user
     * @throws DaoException if a SQL exception occurred
     */
    User select(User user) throws DaoException;

    /**
     * This method used to add one user to the database.If the parameter is
     * null,the method throw NullPointerException.
     *
     * @param    user              one user. It will be added and returned
     * @return   NewsPiece         the added user
     * @throws DaoException if a SQL exception occurred
     */
    User insert(User user) throws DaoException;

    /**
     * This method used to delete one user from the database and returns boolean value:
     * if operation finished without exception, the method returns true. Otherwise it returns false.
     * If the parameter @param tag is null,the method throw NullPointerException.
     *
     * @param    user              one user. It will be removed
     * @return   boolean           this value means status of operation.
     * @throws DaoException if a SQL exception occurred
     */
    boolean delete(User user) throws DaoException;

    /**
     * This method used to edit the user.If one of
     * parameters are null,the method throw NullPointerException.
     *
     * @param    oldObject           old information about the user.
     * @param    newObject           new information about the user.
     * @return   User                updated user
     * @throws DaoException if a SQL exception occurred
     */
    User update(User oldObject, User newObject) throws DaoException;
}
