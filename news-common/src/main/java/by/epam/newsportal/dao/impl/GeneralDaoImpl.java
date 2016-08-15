package by.epam.newsportal.dao.impl;

import by.epam.newsportal.dao.DaoException;
import by.epam.newsportal.dao.GeneralDao;
import by.epam.newsportal.entity.Author;
import by.epam.newsportal.entity.NewsPiece;
import by.epam.newsportal.entity.Tag;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GeneralDaoImpl implements GeneralDao {
    public static final String TAG_ID = "TAG_ID";
    public static final String AUTHOR_ID = "AUTHOR_ID";

    private DataSource dataSource;

    public static final String ADD_NEWS_AUTHOR = "INSERT INTO news_author (news_id,author_id) VALUES (?,?)";
    public static final String ADD_NEWS_TAG = "INSERT INTO news_tag (news_id,tag_id) VALUES (?,?)";

    public GeneralDaoImpl(){}
    public GeneralDaoImpl(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public List<Author> addNewsAuthor(Set<Author> authors, NewsPiece newsPiece) throws DaoException {
        List<Author> authorsList = new ArrayList<Author>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(ADD_NEWS_AUTHOR,new String[]{AUTHOR_ID});
            for (Author author : authors){
                statement.setLong(1, newsPiece.getNewsId());
                statement.setLong(2, author.getAuthorId());
                statement.executeUpdate();

                ResultSet rs = statement.getGeneratedKeys();
                if(rs.next()){
                    Author insertedAuthor = new Author();
                    Integer intId = rs.getInt(1);
                    Long newId = intId.longValue();
                    insertedAuthor.setAuthorId(newId);
                    authorsList.add(insertedAuthor);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("GeneralDaoImpl Exception",e);
        }finally {
            try {
                statement.close();
                if (connection != null) {
                    DataSourceUtils.releaseConnection(connection, dataSource);
                }
            } catch (SQLException e) {
                throw new DaoException("GeneralDaoImpl Exception",e);
            }
        }
        return authorsList;
    }

    public List<Tag> addNewsTag(Set<Tag> tags, NewsPiece newsPiece) throws DaoException {
        List<Tag> tagList = new ArrayList<Tag>();
        Connection connection = null;
        PreparedStatement statement = null;
        try  {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(ADD_NEWS_TAG,new String[]{TAG_ID});
            for (Tag tag : tags){
                statement.setLong(1, newsPiece.getNewsId());
                statement.setLong(2, tag.getTagId());
                statement.executeUpdate();

                ResultSet rs = statement.getGeneratedKeys();
                if(rs.next()){
                    Tag insertedTag = new Tag();
                    insertedTag.setTagId(rs.getLong(1));
                    tagList.add(insertedTag);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("GeneralDaoImpl Exception",e);
        }finally {
            try {
                statement.close();
                if (connection != null) {
                    DataSourceUtils.releaseConnection(connection, dataSource);
                }
            } catch (SQLException e) {
                throw new DaoException("GeneralDaoImpl Exception",e);
            }
        }
        return tagList;
    }

    public List<Author> selectAll() throws DaoException {
        return null;
    }

    public Author select(Author entity) throws DaoException {
        return null;
    }

    public Author insert(Author entity) throws DaoException {
        return null;
    }

    public boolean delete(Author entity) throws DaoException {
        return false;
    }

    public Author update(Author oldObject, Author newObject) throws DaoException {
        return null;
    }

}
