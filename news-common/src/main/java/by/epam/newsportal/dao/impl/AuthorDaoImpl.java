package by.epam.newsportal.dao.impl;

import by.epam.newsportal.dao.AuthorDao;
import by.epam.newsportal.dao.DaoException;
import by.epam.newsportal.entity.Author;
import by.epam.newsportal.entity.NewsPiece;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.datasource.DataSourceUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AuthorDaoImpl implements AuthorDao {
    public static final String AUTHOR_ID = "AUTHOR_ID";
    public static final String AUTHOR_NAME = "AUTHOR_NAME";
    public static final String EXPIRED = "EXPIRED";

    public static final String SELECT_ALL = "SELECT * FROM author WHERE EXPIRED IS NULL";
    public static final String ADD_AUTHOR_TO_NEWS = "INSERT INTO news_author(news_id,author_id) VALUES(?,?)";
    public static final String SEARCH_AUTHOR = "SELECT * FROM author WHERE author_id=?";
    public static final String SELECT_NEWS_AUTHOR = "SELECT * FROM news_author JOIN author " +
            "ON author.author_id=news_author.author_id WHERE news_author.news_id=?";
    public static final String DELETE_AUTHOR = "UPDATE author SET expired=? WHERE author_id=?";

    private BasicDataSource dataSource;

    public AuthorDaoImpl(){}

    public AuthorDaoImpl(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Author select(Author entity) throws DaoException {
        Author author = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try  {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(SEARCH_AUTHOR, new String[]{AUTHOR_ID});
            statement.setLong(1, entity.getAuthorId());
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                author = new Author();
                author.setAuthorId(rs.getLong(AUTHOR_ID));
                author.setName(rs.getString(AUTHOR_NAME));
                author.setExpiredDate(rs.getDate(EXPIRED));
            }
        } catch (SQLException e) {
            throw new DaoException("AuthorDaoImpl Exception",e);
        }finally {
            try {
                statement.close();
                if (connection != null) {
                    DataSourceUtils.releaseConnection(connection, dataSource);
                }
            } catch (SQLException e) {
                throw new DaoException("AuthorDaoImpl Exception",e);
            }
        }
        return author;
    }

    public List<Author> select(NewsPiece newsPiece) throws DaoException {
        List<Author> authors = new ArrayList<Author>();
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(SELECT_NEWS_AUTHOR);
            statement.setLong(1, newsPiece.getNewsId());
            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                Author selectedAuthor = new Author();
                selectedAuthor.setAuthorId(rs.getLong(AUTHOR_ID));
                selectedAuthor.setName(rs.getString(AUTHOR_NAME));
                selectedAuthor.setExpiredDate(rs.getDate(EXPIRED));
                authors.add(selectedAuthor);
            }
        } catch (SQLException e) {
            throw new DaoException("AuthorDaoImpl Exception",e);
        }finally {
            try {
                statement.close();
                if (connection != null) {
                    DataSourceUtils.releaseConnection(connection, dataSource);
                }
            } catch (SQLException e) {
                throw new DaoException("AuthorDaoImpl Exception",e);
            }
        }
        return authors;
    }

    public Author addAuthorToNews(Author author, NewsPiece newsPiece) throws DaoException {
        Author insertedAuthor = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(ADD_AUTHOR_TO_NEWS, new String[]{AUTHOR_ID});
            statement.setLong(1,newsPiece.getNewsId());
            statement.setLong(2,author.getAuthorId());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()){
                insertedAuthor = new Author();
                insertedAuthor.setAuthorId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new DaoException("AuthorDaoImpl Exception",e);
        }finally {
            try {
                statement.close();
                if (connection != null) {
                    DataSourceUtils.releaseConnection(connection, dataSource);
                }
            } catch (SQLException e) {
                throw new DaoException("AuthorDaoImpl Exception",e);
            }
        }
        return insertedAuthor;
    }

    public List<Author> selectAll() throws DaoException {
        List<Author> authors = new ArrayList<Author>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(SELECT_ALL);
            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                Author author = new Author();
                author.setAuthorId(rs.getLong(AUTHOR_ID));
                author.setName(rs.getString(AUTHOR_NAME));
                authors.add(author);
            }
        } catch (SQLException e) {
            throw new DaoException("AuthorDaoImpl Exception",e);
        }finally {
            try {
                statement.close();
                if (connection != null) {
                    DataSourceUtils.releaseConnection(connection, dataSource);
                }
            } catch (SQLException e) {
                throw new DaoException("AuthorDaoImpl Exception",e);
            }
        }
        return authors;
    }

    public boolean delete(Author author) throws DaoException {
        boolean isDelete = false;
        Connection connection = null;
        PreparedStatement statement = null;
        java.sql.Date currentDate = getCurrentDate();
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(DELETE_AUTHOR);
            statement.setDate(1,currentDate);
            statement.setLong(2,author.getAuthorId());
            int updatedRow = statement.executeUpdate();

            if(updatedRow > 0){
                isDelete = true;
            }
        } catch (SQLException e) {
            throw new DaoException("AuthorDaoImpl Exception",e);
        }finally {
            try {
                statement.close();
                if (connection != null) {
                    DataSourceUtils.releaseConnection(connection, dataSource);
                }
            } catch (SQLException e) {
                throw new DaoException("AuthorDaoImpl Exception",e);
            }
        }
        return isDelete;
    }

    public java.sql.Date getCurrentDate(){
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        return sqlDate;
    }

    public Author update(Author oldObject, Author newObject) throws DaoException {
        return null;
    }

    public Author insert(Author entity) throws DaoException {
        return null;
    }

}
