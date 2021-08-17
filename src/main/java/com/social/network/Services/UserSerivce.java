package com.social.network.Services;

import com.social.network.Dao.GenericDao;
import com.social.network.Model.Post;
import com.social.network.Model.Role;
import com.social.network.Model.User;
import com.social.network.PayLoad.ChangePasswordRequest.ChangePasswordRequest;
import com.social.network.PayLoad.LoginRequest.LoginRequest;
import com.social.network.PayLoad.SignUpRequest.SignupRequest;
import com.social.network.exceptions.UserExistException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserSerivce implements IUserService {

    private Logger log;

    private GenericDao<User>userGenericDao;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserSerivce(GenericDao<User> userGenericDao) {
        this.userGenericDao = userGenericDao;
        log = LogManager.getLogger(UserSerivce.class);
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(SignupRequest signupRequest) {
        User user = new User();
        //Для каждого нового юзера устанавливается дефолтная роль USER
        Role role = new Role();
        role.setIdRole(1);
        //проверка нет ли пользователя с таким именем
        User timeUser = findByName(signupRequest.getUserName());
        if (timeUser != null) {
            throw new UserExistException("The user " + user.getUsername() + " already exist. Please change your username");
        } else {
            user.setUsername(signupRequest.getUserName());
            user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
            user.setBirthday(signupRequest.getBirthday());
            user.setEmail(signupRequest.getEmail());
            user.setRole(role);
        }
        try {
            log.info("Saving User {}", user.getEmail());
            userGenericDao.add(user);
            return user;
        } catch (Exception e) {
            log.error("Error during registration. {}", e.getMessage());
            throw new UserExistException("The user " + user.getUsername() + " already exist. Please check credentials");
        }

    }

    @Override
    public void setRole(int id, Role role) {
        User user = userGenericDao.find(id);
        if(user == null){
            throw new UserExistException("The user " + id + "not found");
        } else {
            user.setRole(role);
            userGenericDao.update(user);
        }
    }

    @Override
    public User findByName(String name) {
        User timeUser = null;
        try {
            EntityManager em = userGenericDao.getEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<User> query = cb.createQuery(User.class);
            Root<User> user = query.from(User.class);
            Predicate userPredicate = cb.equal(user.get("username"), name);
            query.select(user).where(userPredicate);
            timeUser = em.createQuery(query).setMaxResults(1).getSingleResult();
        } catch (Exception e) {
            log.error("Cannot find User with this name");
        }
        return timeUser;
    }

    @Override
    public User findByNamePassword(LoginRequest loginRequest) {
        User user = findByName(loginRequest.getUsername());
        if (user != null) {
            if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User changePassowrd(ChangePasswordRequest changePasswordRequest){
        LoginRequest loginRequest = new LoginRequest(changePasswordRequest.getUserName(),changePasswordRequest.getOldPassword());
        User user = findByNamePassword(loginRequest);
         if(user != null){
             user.setPassword(passwordEncoder.encode(changePasswordRequest.getPassword()));
             userGenericDao.update(user);
             return user;
         } else {
             throw new UserExistException("Invalid username or password");
         }
    }

    @Override
    public User changeUserName(int userID, String userName){
        User user = userGenericDao.find(userID);
        User timeUser = findByName(userName);
        if(timeUser == null){
            user.setUsername(userName);
            userGenericDao.update(user);
            return user;
        }else {
            throw new UserExistException("User with this name already exists");
        }
    }

    @Override
    public User changeEmail(int userID, String email){
        User user = userGenericDao.find(userID);
        if(user != null){
            user.setEmail(email);
            userGenericDao.update(user);
            return user;
        } else {
            throw new UserExistException("No user with such id");
        }
    }

    @Override
    public User addNewfriend(int userID, int friendID){
        User user = userGenericDao.find(userID);
        User friend = userGenericDao.find(friendID);
        user.getFriends().add(friend);
        userGenericDao.update(user);
        return friend;
    }

    @Override
    public User deleteFriend(int userID, int friendID){
        User user = userGenericDao.find(userID);
        User friend = userGenericDao.find(friendID);
        if(user.getFriends().contains(friend)){
            user.getFriends().remove(friend);
            userGenericDao.update(user);
            return friend;
        } else {
            throw new UserExistException("No user with such id");
        }
    }

    @Override
    public List<User> getSubscribers(int userID){
        User userTest = userGenericDao.find(userID);

        EntityManager em = userGenericDao.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> user = query.from(User.class);
        Join<User, User> friendsJoin = user.join("friends");
        Predicate userPredicate = cb.equal(friendsJoin.get("id"), userID);
        query.select(user).where(userPredicate);
        List<User> users = em.createQuery(query).getResultList();

        users.removeIf(us -> userTest.getFriends().contains(us));

        System.out.println(users.size());
        users.forEach(System.out::println);
        return users;
    }

    public List<User> getFriends(int userID){
        User userTest = userGenericDao.find(userID);

        EntityManager em = userGenericDao.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> user = query.from(User.class);
        Join<User, User> friendsJoin = user.join("friends");
        Predicate userPredicate = cb.equal(friendsJoin.get("id"), userID);
        query.select(user).where(userPredicate);
        List<User> users = em.createQuery(query).getResultList();

        users.removeIf(us -> !userTest.getFriends().contains(us));

        System.out.println(users.size());
        users.forEach(System.out::println);
        return users;
    }

    @Override
    public User findById(int userID) {
        User user = userGenericDao.find(userID);
        if(user != null){
            return user;
        } else {
            throw new UserExistException("No user with such id");
        }
    }

    @Override
    public List<User>findallUsers(){
        return userGenericDao.findAll();
    }

    @Override
    public Set<Post>openNews(int userID){
        List<User>users = getFriends(userID);
        Set<Post> posts = new HashSet<>();
        for(User us : users){
            posts.addAll(us.getPosts());
        }
        return posts;
    }

}
