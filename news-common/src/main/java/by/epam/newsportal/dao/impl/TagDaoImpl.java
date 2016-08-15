package by.epam.newsportal.dao.impl;

import by.epam.newsportal.dao.DaoException;
import by.epam.newsportal.dao.TagDao;
import by.epam.newsportal.entity.NewsPiece;
import by.epam.newsportal.entity.Tag;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TagDaoImpl implements TagDao {
    public static final String TAG_ID = "TAG_ID";
    public static final String TAG_NAME = "TAG_NAME";
    private DataSource dataSource;
    public static final String ADD_TAG_TO_NEWS = "INSERT INTO news_tag(news_id,tag_id) VALUES(?,?)";
    public static final String SELECT_TAG_BY_ID = "SELECT * FROM news_tag " +
                                                  "JOIN tag ON tag.tag_id=news_tag.tag_id " +
                                                  "WHERE news_tag.news_id=?";
    public static final String SELECT_TAG_BY_NAME = "SELECT * FROM tag WHERE tag_name=?";
    public static final String INSERT_TAG = "INSERT INTO tag(TAG_NAME) VALUES (?)";
    public TagDaoImpl(){}
    public TagDaoImpl(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public Set<Tag> select(NewsPiece newsPiece) throws DaoException {
        Set<Tag> tags = new HashSet<Tag>();
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(SELECT_TAG_BY_ID);
            statement.setLong(1, newsPiece.getNewsId());
            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                Tag selectedTag = new Tag();
                selectedTag.setTagId(rs.getLong(TAG_ID));
                selectedTag.setName(rs.getString(TAG_NAME));
                tags.add(selectedTag);
            }
        } catch (SQLException e) {
            throw new DaoException("TagDaoImpl Exception",e);
        }finally {
            try {
                statement.close();
                if (connection != null) {
                    DataSourceUtils.releaseConnection(connection, dataSource);
                }
            } catch (SQLException e) {
                throw new DaoException("TagDaoImpl Exception",e);
            }
        }
        return tags;
    }

    public Tag addTagToNews(NewsPiece newsPiece, Tag tag) throws DaoException {
        Tag insertedTag = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(ADD_TAG_TO_NEWS, new String[]{TAG_ID});
            statement.setLong(1,newsPiece.getNewsId());
            statement.setLong(2,tag.getTagId());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()){
                insertedTag = new Tag();
                insertedTag.setTagId(rs.getLong(1));
            }
        }catch (SQLException e) {
            throw new DaoException("TagDaoImpl Exception",e);
        }finally {
            try {
                statement.close();
                if (connection != null) {
                    DataSourceUtils.releaseConnection(connection, dataSource);
                }
            } catch (SQLException e) {
                throw new DaoException("TagDaoImpl Exception",e);
            }
        }
        return insertedTag;
    }

    public Tag select(Tag entity) throws DaoException {
        Tag selectedTag = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(SELECT_TAG_BY_NAME,new String[]{TAG_ID});
            statement.setString(1, entity.getName());
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                selectedTag = new Tag();
                selectedTag.setTagId(rs.getLong(TAG_ID));
                selectedTag.setName(rs.getString(TAG_NAME));
            }
        } catch (SQLException e) {
            throw new DaoException("TagDaoImpl Exception",e);
        }finally {
            try {
                statement.close();
                if (connection != null) {
                    DataSourceUtils.releaseConnection(connection, dataSource);
                }
            } catch (SQLException e) {
                throw new DaoException("TagDaoImpl Exception",e);
            }
        }
        return selectedTag;
    }

    public Tag insert(Tag entity) throws DaoException {
        Tag insertedTag = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(INSERT_TAG, new String[]{TAG_ID});
            statement.setString(1, entity.getName());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()){
                insertedTag = new Tag();
                insertedTag.setTagId(rs.getLong(1));
                insertedTag.setName(entity.getName());
            }
        } catch (SQLException e) {
            throw new DaoException("TagDaoImpl Exception",e);
        }finally {
            try {
                statement.close();
                if (connection != null) {
                    DataSourceUtils.releaseConnection(connection, dataSource);
                }
            } catch (SQLException e) {
                throw new DaoException("TagDaoImpl Exception",e);
            }
        }
        return insertedTag;
    }

    public boolean delete(Tag entity) throws DaoException {
        return false;
    }

    public Tag update(Tag oldObject, Tag newObject) throws DaoException {
        return null;
    }
    public List<Tag> selectAll() throws DaoException {
        return null;
    }

}
