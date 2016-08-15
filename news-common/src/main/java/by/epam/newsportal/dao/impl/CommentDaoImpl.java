package by.epam.newsportal.dao.impl;

import by.epam.newsportal.dao.CommentDao;
import by.epam.newsportal.dao.DaoException;
import by.epam.newsportal.entity.Comment;
import by.epam.newsportal.entity.NewsPiece;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CommentDaoImpl implements CommentDao{
    public static final String COMMENT_ID = "COMMENT_ID";
    public static final String COMMENT_TEXT = "COMMENT_TEXT";
    public static final String CREATION_DATE = "CREATION_DATE";

    public static final String DELETE_COMMENT = "DELETE FROM comments WHERE comment_id=?";
    public static final String DELETE_ALL_NEWS_COMMENT = "DELETE FROM comments WHERE news_id=?";
    public static final String ADD_COMMENT_TO_NEWS = "INSERT INTO comments (news_id,comment_id,comment_text,creation_date)" +
                                                     " VALUES(?,?,?,?)";
    public static final String SELECT_NEWS_COMMENT = "SELECT * FROM comments WHERE news_id=? ORDER BY creation_date DESC";

    private DataSource dataSource;

    public CommentDaoImpl(){}
    public CommentDaoImpl(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public List<Comment> select(NewsPiece newsPiece) throws DaoException {
        List<Comment> comments = new LinkedList<Comment>();
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(SELECT_NEWS_COMMENT);
            statement.setLong(1, newsPiece.getNewsId());
            ResultSet rs = statement.executeQuery();

            while (rs.next()){
                Comment comment = new Comment();
                comment.setCommentId(rs.getLong(COMMENT_ID));
                comment.setCommentText(rs.getString(COMMENT_TEXT));
                comment.setCreationDate(rs.getDate(CREATION_DATE));
                comments.add(comment);
            }
        } catch (SQLException e) {
            throw new DaoException("CommentDaoImpl Exception",e);
        }finally {
            try {
                statement.close();
                if (connection != null) {
                    DataSourceUtils.releaseConnection(connection, dataSource);
                }
            } catch (SQLException e) {
                throw new DaoException("CommentDaoImpl Exception",e);
            }
        }
        return comments;
    }

    public List<Comment> addCommentsToNews(NewsPiece newsPiece, List<Comment> newComments) throws DaoException {
        List<Comment> comments = new ArrayList<Comment>();
        Connection connection = null;
        PreparedStatement statement = null;
        try  {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(ADD_COMMENT_TO_NEWS,new String[]{COMMENT_ID});
            for(Comment comment : newComments){
                statement.setLong(1, newsPiece.getNewsId());
                statement.setLong(2,comment.getCommentId());
                statement.setString(3, comment.getCommentText());
                statement.setDate(4, new Date(comment.getCreationDate().getTime()));
                statement.executeUpdate();

                ResultSet rs = statement.getGeneratedKeys();
                if(rs.next()){
                    Comment newComment = new Comment();
                    newComment.setCommentId(rs.getLong(1));
                    comments.add(newComment);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("CommentDaoImpl Exception",e);
        }finally {
            try {
                statement.close();
                if (connection != null) {
                    DataSourceUtils.releaseConnection(connection, dataSource);
                }
            } catch (SQLException e) {
                throw new DaoException("CommentDaoImpl Exception",e);
            }
        }
        return comments;
    }

    public boolean deleteNewsComments(NewsPiece newsPiece) throws DaoException {
        boolean isDelete = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try  {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(DELETE_ALL_NEWS_COMMENT);
            statement.setLong(1, newsPiece.getNewsId());
            int rowCount = statement.executeUpdate();
            if(rowCount > 0){
                isDelete = true;
            }
        } catch (SQLException e) {
            throw new DaoException("CommentDaoImpl Exception",e);
        }finally {
            try {
                statement.close();
                if (connection != null) {
                    DataSourceUtils.releaseConnection(connection, dataSource);
                }
            } catch (SQLException e) {
                throw new DaoException("CommentDaoImpl Exception",e);
            }
        }
        return isDelete;
    }

    public boolean delete(Comment comment) throws DaoException {
        boolean isDelete = false;
        Connection connection = null;
        PreparedStatement statement = null;
        try  {
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(DELETE_COMMENT);
            statement.setLong(1, comment.getCommentId());
            int rowCount = statement.executeUpdate();
            if(rowCount > 0){
                isDelete = true;
            }
        } catch (SQLException e) {
            throw new DaoException("CommentDaoImpl Exception",e);
        }finally {
            try {
                statement.close();
                if (connection != null) {
                    DataSourceUtils.releaseConnection(connection, dataSource);
                }
            } catch (SQLException e) {
                throw new DaoException("CommentDaoImpl Exception",e);
            }
        }
        return isDelete;
    }

    public Comment update(Comment oldObject, Comment newObject) throws DaoException {
        return null;
    }

    public List<Comment> selectAll() throws DaoException {
        return null;
    }

    public Comment select(Comment entity) throws DaoException {
        return null;
    }

    public Comment insert(Comment entity) throws DaoException {
        return null;
    }

}
