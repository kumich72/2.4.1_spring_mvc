package web.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import web.config.HibernateConfig;
import web.model.Role;
import web.model.User;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserHibernateDAO implements IUserDAO {
    private static SessionFactory sessionFactory;
    private Configuration configuration;

    @Autowired
    public UserHibernateDAO () {
        if (configuration == null) {
            ApplicationContext ctx = new AnnotationConfigApplicationContext(HibernateConfig.class);
            configuration = (Configuration) ctx.getBean("getConfiguration");
        }
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
    }

    public UserHibernateDAO(Configuration configuration) {
        if (configuration != null) {
            this.configuration = configuration;
        }
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
    }

    private SessionFactory createSessionFactory() {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public boolean addUser(String name, String email, String password) {
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

    public boolean editUser(Long id, String name, String email, String password) {
        try {
            Session session = sessionFactory.openSession();
            User user = (User) session.load(User.class, id);
            if (user != null) {
                Transaction transaction = session.beginTransaction();
                user.setEmail(email);
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

    public boolean deleteUser(Long id) {
        try {
            Session session = sessionFactory.openSession();
            User user = (User) session.load(User.class, id);
            if (user != null) {
                Transaction transaction = session.beginTransaction();
                session.delete(user);
                transaction.commit();
                session.close();
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
            Session session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(User.class);
            int count = criteria
                    .add(Restrictions.eq("name", name))
                    .add(Restrictions.eq("password", password))
                    .add(Restrictions.eq("role", "admin"))
                    .list()
                    .size();
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
            Query query = session.createQuery("FROM User WHERE name = :name AND password = :password ");
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
            Query query = session.createQuery("FROM User WHERE name = :name");
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
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM Role WHERE user_id = :id");
        query.setParameter("id", user.getId());
        roles = query.list();
        return roles;
    }


    @Override
    public User getUserById(Long id)  {
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
            Transaction transaction = session.beginTransaction();
            users = session.createQuery("FROM User").list();
            transaction.commit();
            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
        return null;
    }

    @Override
    public boolean addUser(User user)  {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean checkUserExist(String name) {
        try {
            Session session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(User.class);
            int count = criteria.add(Restrictions.eq("name", name)).list().size();
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
}
