package by.epam.newsportal.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * This class used like transfer object for User entity.
 */

public class User {
    public static final Long USER_ID = 1L;
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String NAME = "name";
    private Long userId;
    private String name;
    private String login;
    private String password;
    private UserRole role;

    public User(Long userId, String name, String login, String password,UserRole role) {
        this.userId = userId;
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = role;
    }
    public User(){
        this.name = NAME;
        this.userId = USER_ID;
        this.login = LOGIN;
        this.password = PASSWORD;
        this.role = UserRole.USER;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(this.getClass() != obj.getClass()){
            return false;
        }
        User temp = (User) obj;
        if(!this.userId.equals(temp.userId)){
            return false;
        }
        if(!this.name.equals(temp.name)){
            return false;
        }
        if(!this.login.equals(temp.login)){
            return false;
        }
        if(!this.password.equals(temp.password)){
            return false;
        }
        if(!this.role.equals(temp.role)){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return 3 * this.userId.hashCode() + 5 * this.name.hashCode() +
                    7 * this.login.hashCode() + 9 * this.password.hashCode() + 7 * this.role.hashCode();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(userId);
        sb.append(name);
        sb.append(login);
        sb.append(password);
        sb.append(role);
        return sb.toString();
    }
}
