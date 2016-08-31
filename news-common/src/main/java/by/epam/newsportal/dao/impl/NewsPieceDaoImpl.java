package by.epam.newsportal.dao.impl;

import by.epam.newsportal.dao.DaoException;
import by.epam.newsportal.dao.NewsPieceDao;
import by.epam.newsportal.entity.Author;
import by.epam.newsportal.entity.Comment;
import by.epam.newsportal.entity.NewsPiece;
import by.epam.newsportal.entity.Tag;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class NewsPieceDaoImpl implements NewsPieceDao{
    public static final String NEWS_ID = "NEWS_ID";
    public static final String TITLE = "TITLE";
    public static final String SHORT_TEXT = "SHORT_TEXT";
    public static final String FULL_TEXT = "FULL_TEXT";
    public static final String CREATION_DATE = "CREATION_DATE";
    public static final String MODIFICATION_DATE = "MODIFICATION_DATE";
    public static final String COMMENT_ID = "COMMENT_ID";
    public static final String COMMENT_TEXT = "COMMENT_TEXT";
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
            "GROUP BY (news.NEWS_ID, news.TITLE, news.SHORT_TEXT, news.FULL_TEXT, news.CREATION_DATE, news.MODIFICATION_DATE) " +
            "ORDER BY com DESC";
    public static final String SELECT_NEWS_MESSAGE = "SELECT * FROM news WHERE news.news_id=?";
    public static final String SELECT_NEWS_TAGS = "SELECT tag.tag_id, tag.tag_name " +
            "FROM tag RIGHT JOIN news_tag " +
            "ON news_tag.tag_id=tag.tag_id " +
            "WHERE news_tag.news_id=?";
    public static final String SELECT_NEWS_AUTHORS = "SELECT author.author_id,author.author_name " +
            "FROM author RIGHT JOIN news_author ON news_author.author_id=author.author_id " +
            "WHERE news_author.news_id=?";
    public static final String SELECT_NEWS_COMMENTS = "SELECT comment_id, comment_text, creation_date " +
            "FROM comments WHERE news_id = ?";

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
                while(rs.next()){
                    NewsPiece newsPiece = new NewsPiece();
                    newsPiece.setNewsId(rs.getLong(NEWS_ID));
                    newsPiece.setTitle(rs.getString(TITLE));
                    newsPiece.setShortText(rs.getString(SHORT_TEXT));
                    newsPiece.setFullText(rs.getString(FULL_TEXT));
                    newsPiece.setCreationDate(rs.getDate(CREATION_DATE));
                    newsPiece.setModificationDate(rs.getDate(MODIFICATION_DATE));
                    news.add(newsPiece);
                }
                setNewsAuthor(news);
                setNewsTags(news);
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
            setNewsTags(news);
            setNewsAuthor(news);
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
            if(Long.valueOf(rs.getLong("com")) != null){
                newsPiece.setCommentCount(Long.valueOf(rs.getLong("com")));
            }
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
                news = new NewsPiece();
                news.setNewsId(rs.getLong(NEWS_ID));
                news.setTitle(rs.getString(TITLE));
                news.setShortText(rs.getString(SHORT_TEXT));
                news.setFullText(rs.getString(FULL_TEXT));
                news.setCreationDate(rs.getDate(CREATION_DATE));
                news.setModificationDate(rs.getDate(MODIFICATION_DATE));
            }
            List<NewsPiece> newsList = createOneElementList(news);
            setNewsTags(newsList);
            setNewsAuthor(newsList);
            setComments(newsList);
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

    private void setNewsTags(List<NewsPiece> news) throws DaoException{
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(SELECT_NEWS_TAGS);
            for (NewsPiece newsPiece : news){
                statement.setLong(1,newsPiece.getNewsId());
                ResultSet resultSet = statement.executeQuery();
                Set<Tag> tags = new HashSet<>();
                while (resultSet.next()){
                    Tag tag = new Tag();
                    tag.setTagId(resultSet.getLong("tag_id"));
                    tag.setName(resultSet.getString("tag_name"));
                    tags.add(tag);
                }
                newsPiece.setTags(tags);
            }
        }catch (SQLException e) {
            throw new DaoException("NewsPieceDaoImpl Exception", e);
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
    }
    private void setNewsAuthor(List<NewsPiece> news) throws DaoException{
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(SELECT_NEWS_AUTHORS);
            for (NewsPiece newsPiece : news){
                statement.setLong(1,newsPiece.getNewsId());
                ResultSet resultSet = statement.executeQuery();
                Set<Author> authors = new HashSet<>();
                while (resultSet.next()){
                    Author author = new Author();
                    author.setAuthorId(resultSet.getLong("author_id"));
                    author.setName(resultSet.getString("author_name"));
                    authors.add(author);
                }
                newsPiece.setAuthors(authors);
            }
        }catch (SQLException e) {
            throw new DaoException("NewsPieceDaoImpl Exception", e);
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
    }
    private void setComments(List<NewsPiece> news) throws DaoException{
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(SELECT_NEWS_COMMENTS);
            for (NewsPiece newsPiece : news){
                statement.setLong(1,newsPiece.getNewsId());
                ResultSet resultSet = statement.executeQuery();
                List<Comment> comments = new LinkedList<>();
                while (resultSet.next()){
                    Comment comment = new Comment();
                    comment.setCommentId(resultSet.getLong(COMMENT_ID));
                    comment.setCommentText(resultSet.getString(COMMENT_TEXT));
                    comment.setCreationDate(resultSet.getDate(CREATION_DATE));
                    comments.add(comment);
                }
                newsPiece.setComments(comments);
            }
        }catch (SQLException e) {
            throw new DaoException("NewsPieceDaoImpl Exception", e);
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
    }
    private List<NewsPiece> createOneElementList(NewsPiece newsPiece){
        List<NewsPiece> newsList = new ArrayList<>();
        newsList.add(newsPiece);
        return newsList;
    }
}
