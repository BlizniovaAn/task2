package by.epam.newsportal.dao;

/**
 * This class used to wrap up SQL exceptions and to transfer this exception(s) to next layer.
 */
public class DaoException extends Exception {

    public DaoException(){}

    public DaoException(String message){super(message);}

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }

}
