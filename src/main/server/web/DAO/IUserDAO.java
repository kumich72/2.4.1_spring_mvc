package web.DAO;

import web.model.Role;
import web.model.User;
import java.sql.SQLException;
import java.util.List;

public interface IUserDAO {
    User getUserById(Long id) throws SQLException;

    List<User> getAllUsers() ;

    boolean addUser(User user);

    boolean editUser(Long id, String name, String password, String email);

    boolean deleteUser(Long id);

    boolean userIsAdmin(String name, String password);

    User getUserByNameAndPassword(String name, String password);

    boolean userIsAdmin(Long id);

    User getUserByName(String name);

    List<Role> getRolesByUser(User user);
}
