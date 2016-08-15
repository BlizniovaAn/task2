package by.epam.newsportal.dao;

import java.util.List;

public interface Dao<T> {
    /**
     * This method used to select all object of T-class.It returns list of objects.
     */
    List<T> selectAll() throws DaoException;

    /**
     * This method used to select one object of T-class.It returns this object.
     */
    T select(T entity) throws DaoException;

    /**
     * This method used to insert object of T-class.It returns this object.
     */
    T insert(T entity) throws DaoException;

    /**
     * This method used to delete one object of T-class.It returns boolean :
     * if operation finished without exception, the method returns true.
     * Otherwise it returns false.
     */
    boolean delete(T entity) throws DaoException;

    /**
     * This method used to edit object of T-class.It returns edited object.
     */
    T update(T oldObject, T newObject) throws DaoException;
}
