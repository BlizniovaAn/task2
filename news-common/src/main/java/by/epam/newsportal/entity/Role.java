package by.epam.newsportal.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * This class used like transfer object for Role entity.
 */

public class Role {
    public static final String ROLE_NAME = "roleName";
    private User user;
    private String roleName;

    public Role(User user, String name) {
        this.user = user;
        this.roleName = name;
    }
    public Role(){
        this.roleName = ROLE_NAME;
        this.user = new User();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return roleName;
    }

    public void setName(String name) {
        this.roleName = name;
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
        Role temp = (Role) obj;
        if(!this.user.equals(temp.user)){
            return false;
        }
        if(!this.roleName.equals(temp.roleName)){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return 3 * this.user.hashCode() + 5 * this.roleName.hashCode();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(user);
        sb.append(roleName);
        return sb.toString();
    }
}
