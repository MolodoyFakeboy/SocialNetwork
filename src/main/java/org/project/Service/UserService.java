package org.project.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.project.Dao.GenericDao;
import org.project.Model.Role;
import org.project.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService implements IUserService {

    private GenericDao<User> genericDao;

    private Logger log;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(GenericDao<User> genericDao) {
        this.genericDao = genericDao;
        log = LogManager.getLogger(UserService.class);
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void add(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            genericDao.add(user);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public User update(User user) {
        try {
            genericDao.update(user);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return user;
    }

    @Override
    public void delete(int id) {
        try {
            genericDao.delete(id);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void setRole(int id, Role role) {
        User user = genericDao.find(id);
        user.setRole(role);
        genericDao.update(user);
    }

    @Override
    public User findByName(String name) {
        User timeUser = null;
        try {
            EntityManager em = genericDao.getEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<User> query = cb.createQuery(User.class);
            Root<User> user = query.from(User.class);
            Predicate userPredicate = cb.equal(user.get("username"), name);
            query.select(user).where(userPredicate);
            timeUser = em.createQuery(query).setMaxResults(1).getSingleResult();
        } catch (Exception e) {
            log.error(e);
        }
        return timeUser;
    }

    @Override
    public User findByNamePassword(String name, String password) {
        User user = findByName(name);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        return genericDao.findAll();
    }

    @Override
    public User find(int id) {
        return genericDao.find(id);
    }
}
