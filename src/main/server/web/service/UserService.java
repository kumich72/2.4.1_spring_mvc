package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.DAO.IUserDAO;
//import web.DAO.UserDaoFactory;
import web.DAO.UserHibernateDAO;
import web.exception.DBException;
import web.model.Role;
import web.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {

    private static UserService userService;
    @Autowired
    private UserHibernateDAO userHibernateDAO;

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    @Override
    public User findByUsername(String name) {
        try {
            User user = userHibernateDAO.getUserByName(name);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Role> getRolesByUser(User user) {
        try {
            List<Role> roles = userHibernateDAO.getRolesByUser(user);
            return roles;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserById(Long id) {
        try {
            User user = userHibernateDAO.getUserById(id);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllUsers() {
//        IUserDAO userDAO = userHibernateDAO;

        List<User> users = new ArrayList<>();
        try {
            users = userHibernateDAO.getAllUsers();
//            users = userDAO.getAllUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean deleteUser(Long id) throws DBException {
        try {
            if (userHibernateDAO.deleteUser(id)) {
                return true;
            }
        } catch (Exception e) {
            throw new DBException(e);
        }
        return false;
    }

    public boolean addUser(User user) throws DBException {
        try {
            if (userHibernateDAO.addUser(user)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new DBException(e);
        }
    }

    public boolean editUser(Long id, String name, String password, String email) throws DBException {
        try {
            if (userHibernateDAO.editUser(id, name, password, email)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new DBException(e);
        }
    }

    public boolean userIsAdmin(String name, String password) throws DBException {
        try {
            if (userHibernateDAO.userIsAdmin(name, password)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new DBException(e);
        }
    }

    public User getUserByNameAndPassword(String name, String password) {
        try {
            User user = userHibernateDAO.getUserByNameAndPassword(name, password);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean userIsAdmin(Long id) throws DBException {
        try {
            if (userHibernateDAO.userIsAdmin(id)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new DBException(e);
        }
    }
}