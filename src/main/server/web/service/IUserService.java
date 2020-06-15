package web.service;

import web.model.Role;
import web.model.User;

import java.util.List;

public interface IUserService {
    List<User> getAllUsers();

    User findByUsername(String name);

    List<Role> getRolesByUser(User user);
}
