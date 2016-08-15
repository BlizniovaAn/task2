package by.epam.newsportal.dao.impl;
import by.epam.newsportal.dao.DaoException;
import by.epam.newsportal.dao.UserDao;
import by.epam.newsportal.entity.User;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class UserDaoImpl implements UserDao{
    private DataSource dataSource;
    public static final String USER_ID = "USER_ID";
    public static final String INSERT_USER = "INSERT INTO users (user_name,login,password) VALUES (?,?,?)";
    public UserDaoImpl(){}
    public UserDaoImpl(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public User insert(User user) throws DaoException {
        User insertedUser = null;
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(INSERT_USER,new String[]{USER_ID});
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()){
                insertedUser = new User();
                insertedUser.setUserId(rs.getLong(USER_ID));
            }
        } catch (SQLException e) {
            throw new DaoException("UserDaoImpl Exception",e);
        }finally {
            try {
                statement.close();
                if (connection != null) {
                    DataSourceUtils.releaseConnection(connection, dataSource);
                }
            } catch (SQLException e) {
              throw new DaoException("UserDaoImpl Exception",e);
            }
        }
        return insertedUser;
    }

    public boolean delete(User entity) throws DaoException {
        return false;
    }

    public User update(User oldObject, User newObject) throws DaoException {
        return null;
    }

    public List<User> selectAll() throws DaoException {
        return null;
    }

    public User select(User entity) throws DaoException {
        return null;
    }


}
