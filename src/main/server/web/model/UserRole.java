package web.model;

import java.util.List;

public class UserRole {
    private User User;
    private List<Role> roles;

    public web.model.User getUser() {
        return User;
    }

    public void setUser(web.model.User user) {
        User = user;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public UserRole(web.model.User user, List<Role> roles) {
        User = user;
        this.roles = roles;
    }
    public UserRole() {
    }

}
