package web.DAO;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.model.Role;
import web.model.User;
import web.repository.RoleRepository;
import web.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserHibernateDAO  implements IUserDAO {

    private final SessionFactory sessionFactory;

    public final UserRepository userRepository;
    public final RoleRepository roleRepository;

    public UserHibernateDAO(SessionFactory sessionFactory, UserRepository userRepository, RoleRepository roleRepository) {
        this.sessionFactory = sessionFactory;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
//    public UserHibernateDAO(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }

    public boolean addUserOld(String name, String email, String password) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            if (!checkUserExist(name)) {
                User user = new User();
                user.setEmail(email);
                user.setName(name);
                user.setPassword(password);
                session.save(user);
                transaction.commit();
                session.close();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addUser(String name, String email, String password) {
        try {
            User user = new User();
            user.setEmail(email);
            user.setName(name);
            user.setPassword(password);
            if (!checkUserExist(name)) {
                userRepository.save(user);
                return true;
            }else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean editUser(Long id, String name, String email, String password, String[] roles) {
        try {
            Optional<User> userFind = userRepository.findById(id);
            if (userFind.isPresent()) {
                User user = userFind.get();
                user.setEmail(email);
                user.setPassword(password);
                userRepository.save(user);
                if (deleteAllRolesUser(user)) {
                    for (String nameRole : roles) {
                        Role role = new Role();
                        role.setName(nameRole);
                        role.setUser(user);
                        roleRepository.save(role);
                    }
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean deleteAllRolesUser(User user) {
        try {
            List<Role> roles = getRolesByUser(user);
            for (Role role : roles) {
                roleRepository.delete(role);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    @Transactional
    public boolean deleteUser(Long id) {
        try {
            Optional<User> userFind = userRepository.findById(id);
            if (userFind.isPresent()) {
                User user = userFind.get();
                List<Role> roles = getRolesByUser(user);
                for (Role role : roles) {
                    roleRepository.delete(role);
                }
                userRepository.delete(user);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean userIsAdmin(String name, String password) {
        try {
            int count = 0;
            Session session = sessionFactory.openSession();
//            Criteria criteria = session.createCriteria(User.class);
//            int count = criteria
//                    .add(Restrictions.eq("name", name))
//                    .add(Restrictions.eq("password", password))
//                    .add(Restrictions.eq("role", "admin"))
//                    .list()
//                    .size();
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User getUserByNameAndPassword(String name, String password) {
        Session session = sessionFactory.openSession();
        try {
            Query query = (Query) session.createQuery("FROM User WHERE name = :name AND password = :password ");
            query.setParameter("name", name);
            query.setParameter("password", password);
            List<User> list = query.list();

            if (list.size() == 1) {
                return list.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
        return null;
    }

    @Override
    public boolean userIsAdmin(Long id) {
        return false;
    }

    @Override
    public User getUserByName(String name) {
        Session session = sessionFactory.openSession();
        try {
            Query query = (Query) session.createQuery("FROM User WHERE name = :name");
            query.setParameter("name", name);
            List<User> list = query.list();

            if (list.size() == 1) {
                return list.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
        return null;
    }

    @Override
    public List<Role> getRolesByUser(User user) {
        List<Role> roles = new ArrayList<>();
        roles = roleRepository.findByUser(user);
        return roles;
    }

    @Override
    public User getUserById(Long id) {
        Session session = sessionFactory.openSession();
        try {
            User user = (User) session.load(User.class, id);
            if (user != null) {
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
        return null;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Session session = sessionFactory.openSession();
        try {
            users = session.createQuery("FROM User").list();
            session.close();
            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
        return null;
    }

    public boolean addUserOld(User user) {
        Session session = sessionFactory.openSession();
        try {
            session.save(user);
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
        return false;
    }

    @Override
    public boolean addUser(User user) {
        try {
//            userRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean checkUserExist(String name) {
        try {
            Session session = sessionFactory.openSession();
            int count =0;
//            Criteria criteria = session.createCriteria(User.class);
//            int count = criteria.add(Restrictions.eq("name", name)).list().size();
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Role> getAllRoles() {
        List<Role> users = new ArrayList<>();
        Session session = sessionFactory.openSession();
        try {
            users = session.createQuery("FROM Role AS r group by r.name").list();
            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
        return null;
    }

    public List<String> getAllNamesRoles() {
        List<Role> usersRoles = new ArrayList<>();
        List<String> names = new ArrayList<>();
        Session session = sessionFactory.openSession();
        try {
            usersRoles = session.createQuery("FROM Role AS r group by r.name").list();
            for (Role role: usersRoles){
                names.add(role.getName());
            }
            return names;
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
        return null;
    }


    public boolean addRolesUser(User user, String[] roles) {
        Session session = sessionFactory.openSession();
        try {
            session.save(user);
            for (String name : roles) {
                Role role = new Role();
                role.setName(name);
                role.setUser(user);
                session.save(role);
                session.close();
            }
            session.close();
            return true;
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        session.close();
        return false;
    }

    public List<String> getRolesNamesByUser(User user) {
        List<String> names = new ArrayList<>();
        List<Role> roles = new ArrayList<>();
        Session session = sessionFactory.openSession();
        Query query = (Query) session.createQuery("FROM Role WHERE user_id = :id");
        query.setParameter("id", user.getId());
        roles = query.list();
        for (Role role: roles){
            names.add(role.getName());
        }
        return names;
    }
}
