package by.epam.newsportal.dao.impl;

import by.epam.newsportal.dao.DaoException;
import by.epam.newsportal.dao.NewsPieceDao;
import by.epam.newsportal.entity.Author;
import by.epam.newsportal.entity.NewsPiece;
import by.epam.newsportal.entity.Tag;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NewsPieceDaoImpl implements NewsPieceDao{
    public static final String NEWS_ID = "NEWS_ID";
    public static final String TITLE = "TITLE";
    public static final String SHORT_TEXT = "SHORT_TEXT";
    public static final String FULL_TEXT = "FULL_TEXT";
    public static final String CREATION_DATE = "CREATION_DATE";
    public static final String MODIFICATION_DATE = "MODIFICATION_DATE";
    public static final int COUNT = 1;

    private DataSource dataSource;
    public static final String SEARCH_BY_AUTHOR_TAGS = "SELECT news.* FROM news JOIN news_tag ON news_tag.NEWS_ID=news.news_id " +
            "  WHERE news.news_id IN (SELECT news.NEWS_ID FROM news_author WHERE news_author.author_id=?) AND news_tag.tag_id=?";
    public static final String SEARCH_BY_TAGS = "SELECT news.* FROM news JOIN news_tag ON news_tag.news_id=news.news_id " +
            "  WHERE news_tag.tag_id=?";
    public static final String SEARCH_BY_AUTHOR = "SELECT news.* FROM news JOIN news_author ON news_author.news_id=news.news_id " +
            "  WHERE news_author.author_id=?";
    public static final String GET_NEWS_COUNT = "SELECT COUNT(*) FROM news";
    public static final String ADD_NEWS = "INSERT INTO news (title,short_text,full_text,creation_date,modification_date) " +
            "VALUES (?,?,?,?,?)";
    public static final String UPDATE_NEWS = "UPDATE news SET title=?,short_text=?,full_text=?,creation_date=?,modification_date=? " +
            " WHERE news_id=?";
    public static final String DELETE_NEWS = "DELETE FROM news WHERE news_id=?";
    public static final String LIST_NEWS = "SELECT news.*, COUNT(comments.COMMENT_ID) as com " +
            "FROM news LEFT JOIN comments ON news.NEWS_ID = comments.NEWS_ID " +
            "GROUP BY (news.NEWS_ID,news.TITLE,news.SHORT_TEXT,news.FULL_TEXT,news.CREATION_DATE,news.MODIFICATION_DATE) " +
            "ORDER BY com DESC";
    public static final String SELECT_NEWS_MESSAGE = "SELECT * FROM news WHERE news.news_id=?";

    public NewsPieceDaoImpl(){}
    public NewsPieceDaoImpl(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public Long getNewsCount() throws DaoException {
        Long newsCount = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(GET_NEWS_COUNT);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                newsCount = rs.getLong(COUNT);
            }
        }catch (SQLException e) {
            throw new DaoException("NewsPieceDaoImpl Exception",e);
        }finally {
            try {
                statement.close();
                if (connection != null) {
                    DataSourceUtils.releaseConnection(connection, dataSource);
                }
            } catch (SQLException e) {
                throw new DaoException("NewsPieceDaoImpl Exception",e);
            }
        }
        return newsCount;
    }

    public List<NewsPiece> find(Author author, List<Tag> tags) throws DaoException {
        List<NewsPiece> news = new ArrayList<NewsPiece>();
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(SEARCH_BY_AUTHOR_TAGS);
            for(Tag tag : tags){
                statement.setLong(1,author.getAuthorId());
                statement.setLong(2,tag.getTagId());
                ResultSet rs = statement.executeQuery();
                if(rs.next()){
                    NewsPiece newsPiece = createNewsPiece(rs);
                    news.add(newsPiece);
                }
            }
        }catch (SQLException e) {
            throw new DaoException("NewsPieceDaoImpl Exception",e);
        }finally {
            try {
                statement.close();
                if (connection != null) {
                    DataSourceUtils.releaseConnection(connection, dataSource);
                }
            } catch (SQLException e) {
                throw new DaoException("NewsPieceDaoImpl Exception",e);
            }
        }
        return news;
    }

    public List<NewsPiece> find(Author author) throws DaoException {
        List<NewsPiece> news = new ArrayList<NewsPiece>();
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(SEARCH_BY_AUTHOR);
                statement.setLong(1,author.getAuthorId());
                ResultSet rs = statement.executeQuery();
                if(rs.next()){
                    NewsPiece newsPiece = createNewsPiece(rs);
                    news.add(newsPiece);
                }
        }catch (SQLException e) {
            throw new DaoException("NewsPieceDaoImpl Exception",e);
        }finally {
            try {
                statement.close();
                if (connection != null) {
                    DataSourceUtils.releaseConnection(connection, dataSource);
                }
            } catch (SQLException e) {
                throw new DaoException("NewsPieceDaoImpl Exception",e);
            }
        }
        return news;
    }

    public List<NewsPiece> find(List<Tag> tags) throws DaoException {
        List<NewsPiece> news = new LinkedList<NewsPiece>();
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(SEARCH_BY_TAGS);
            for(Tag tag : tags){
                statement.setLong(1,tag.getTagId());
                ResultSet rs = statement.executeQuery();
                if(rs.next()){
                    NewsPiece newsPiece = createNewsPiece(rs);
                    news.add(newsPiece);
                }
            }
        }catch (SQLException e) {
            throw new DaoException("NewsPieceDaoImpl Exception",e);
        }finally {
            try {
                statement.close();
                if (connection != null) {
                    DataSourceUtils.releaseConnection(connection, dataSource);
                }
            } catch (SQLException e) {
                throw new DaoException("NewsPieceDaoImpl Exception",e);
            }
        }
        return news;
    }

    public List<NewsPiece> selectAll() throws DaoException {
        List<NewsPiece> news = new LinkedList<NewsPiece>();
        Connection connection = null;
        PreparedStatement statement = null;
        try  {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(LIST_NEWS);
            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                NewsPiece newsPiece = createNewsPiece(rs);
                news.add(newsPiece);
            }
        } catch (SQLException e) {
            throw new DaoException("NewsPieceDaoImpl Exception",e);
        }finally {
            try {
                statement.close();
                if (connection != null) {
                    DataSourceUtils.releaseConnection(connection, dataSource);
                }
            } catch (SQLException e) {
                throw new DaoException("NewsPieceDaoImpl Exception",e);
            }
        }
        return news;
    }

    private NewsPiece createNewsPiece(ResultSet rs) throws DaoException {
        NewsPiece newsPiece = new NewsPiece();
        try {
            newsPiece.setNewsId(rs.getLong(NEWS_ID));
            newsPiece.setTitle(rs.getString(TITLE));
            newsPiece.setShortText(rs.getString(SHORT_TEXT));
            newsPiece.setFullText(rs.getString(FULL_TEXT));
            newsPiece.setCreationDate(rs.getDate(CREATION_DATE));
            newsPiece.setModificationDate(rs.getDate(MODIFICATION_DATE));
        } catch (SQLException e) {
            throw new DaoException("NewsPieceDaoImpl Exception",e);
        }
       return newsPiece;
    }

    public NewsPiece select(NewsPiece entity) throws DaoException {
        NewsPiece news = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try  {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(SELECT_NEWS_MESSAGE,new String[]{NEWS_ID});
            statement.setLong(1, entity.getNewsId());
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                news = createNewsPiece(rs);
            }
        } catch (SQLException e) {
            throw new DaoException("NewsPieceDaoImpl Exception",e);
        }finally {
            try {
                statement.close();
                if (connection != null) {
                    DataSourceUtils.releaseConnection(connection, dataSource);
                }
            } catch (SQLException e) {
                throw new DaoException("NewsPieceDaoImpl Exception",e);
            }
        }
        return news;
    }

    public NewsPiece insert(NewsPiece newsPiece) throws DaoException {
        NewsPiece insertedNews = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try  {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(ADD_NEWS,new String[]{NEWS_ID});
            statement.setString(1, newsPiece.getTitle());
            statement.setString(2, newsPiece.getShortText());
            statement.setString(3, newsPiece.getFullText());
            statement.setDate(4, new Date(newsPiece.getCreationDate().getTime()));
            statement.setDate(5, new Date(newsPiece.getModificationDate().getTime()));
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()){
                insertedNews = new NewsPiece();
                insertedNews.setNewsId(rs.getLong(1));
            }
        } catch (SQLException e) {
            throw new DaoException("NewsPieceDaoImpl Exception",e);
        }finally {
            try {
                statement.close();
                if (connection != null) {
                    DataSourceUtils.releaseConnection(connection, dataSource);
                }
            } catch (SQLException e) {
                throw new DaoException("NewsPieceDaoImpl Exception",e);
            }
        }
        return insertedNews;
    }

    public boolean delete(NewsPiece entity) throws DaoException {
        boolean isDelete = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try  {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(DELETE_NEWS);
            statement.setLong(1, entity.getNewsId());
            int rowCount = statement.executeUpdate();
            if(rowCount > 0){
                isDelete = true;
            }
        } catch (SQLException e) {
            throw new DaoException("NewsPieceDaoImpl Exception",e);
        }finally {
            try {
                statement.close();
                if (connection != null) {
                    DataSourceUtils.releaseConnection(connection, dataSource);
                }
            } catch (SQLException e) {
                throw new DaoException("NewsPieceDaoImpl Exception",e);
            }
        }
        return isDelete;
    }

    public NewsPiece update(NewsPiece oldNewsPiece, NewsPiece newNewsPiece) throws DaoException {
        NewsPiece updatedNews = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try  {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(UPDATE_NEWS, new String[]{NEWS_ID});
            statement.setString(1, newNewsPiece.getTitle());
            statement.setString(2, newNewsPiece.getShortText());
            statement.setString(3, newNewsPiece.getFullText());
            statement.setDate(4, new Date(newNewsPiece.getCreationDate().getTime()));
            statement.setDate(5, new Date(newNewsPiece.getModificationDate().getTime()));
            statement.setLong(6, oldNewsPiece.getNewsId());
            int rowCount = statement.executeUpdate();
            if(rowCount > 0){
                updatedNews = new NewsPiece();
            }
        } catch (SQLException e) {
            throw new DaoException("NewsPieceDaoImpl Exception",e);
        }finally {
            try {
                statement.close();
                if (connection != null) {
                    DataSourceUtils.releaseConnection(connection, dataSource);
                }
            } catch (SQLException e) {
                throw new DaoException("NewsPieceDaoImpl Exception",e);
            }
        }
        return updatedNews;
    }

}
