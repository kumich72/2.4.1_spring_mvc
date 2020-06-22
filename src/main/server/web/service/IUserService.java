package web.service;

import web.model.Role;
import web.model.User;
import web.model.UserRole;

import java.util.List;

public interface IUserService {
    List<User> getAllUsers();

    User findByUsername(String name);

    List<Role> getRolesByUser(User user);

    boolean userIsAdmin(User user);

    List<UserRole> getAllUsersAndRoles();

    List<Role> getAllRoles();

    boolean addRolesUser(User user, String[] roles);
}
