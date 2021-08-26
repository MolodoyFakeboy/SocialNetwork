package com.social.network.Services;

import com.social.network.Dao.GenericDao;
import com.social.network.Dto.UserDTO;
import com.social.network.Facade.PostFacade;
import com.social.network.Facade.UserFacade;
import com.social.network.Model.Group;
import com.social.network.Model.Role;
import com.social.network.PayLoad.ChangePasswordRequest.ChangePasswordRequest;
import com.social.network.PayLoad.LoginRequest.LoginRequest;
import com.social.network.PayLoad.SignUpRequest.SignupRequest;
import com.social.network.Model.User;
import com.social.network.Services.Interfaces.IUserService;
import com.social.network.exceptions.UserExistException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserSerivce implements IUserService {

    private Logger log;

    private GenericDao<User> userGenericDao;

    private PasswordEncoder passwordEncoder;

    private UserFacade userFacade;

    private PostFacade postFacade;

    @Autowired
    public UserSerivce(GenericDao<User> userGenericDao) {
        this.userGenericDao = userGenericDao;
        log = LogManager.getLogger(UserSerivce.class);
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @Autowired
    public void setPostFacade(PostFacade postFacade) {
        this.postFacade = postFacade;
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
        if (user == null) {
            throw new UserExistException("The user " + id + "not found");
        } else {
            user.setRole(role);
            userGenericDao.update(user);
        }
    }

    @Override
    public User setBio(Principal principal, String bio) {
        User user = findByName(principal.getName());
        if (user == null) {
            throw new UserExistException("The user " + principal.getName() + "not found");
        } else {
            user.setBio(bio);
            userGenericDao.update(user);
            return user;
        }
    }

    @Override
    public UserDTO findUserDtoByName(String name) {
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
        return userFacade.getUserProfile(timeUser);
    }

    @Override
    public User findByNamePassword(LoginRequest loginRequest) {
        User user = findByName(loginRequest.getUsername());
        if (user != null) {
            if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                return user;
            }
        } else {
            throw new UserExistException("Invalid username or password");
        }
        return null;
    }

    @Override
    public User changePassowrd(ChangePasswordRequest changePasswordRequest) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(changePasswordRequest.getUserName());
        loginRequest.setPassword(changePasswordRequest.getOldPassword());
        User user = findByNamePassword(loginRequest);
        if (user != null) {
            user.setPassword(passwordEncoder.encode(changePasswordRequest.getPassword()));
            userGenericDao.update(user);
            return user;
        } else {
            throw new UserExistException("Invalid username or password");
        }
    }

    @Override
    public User changeUserName(Principal principal, String userName) {
        User user = findByName(principal.getName());
        User timeUser = findByName(userName);
        if (timeUser == null) {
            user.setUsername(userName);
            userGenericDao.update(user);
            return user;
        } else {
            throw new UserExistException("User with this name already exists");
        }
    }

    @Override
    public User changeEmail(Principal principal, String email) {
        User user = findByName(principal.getName());
        if (user != null) {
            user.setEmail(email);
            userGenericDao.update(user);
            return user;
        } else {
            throw new UserExistException("No user with such id");
        }
    }

    @Override
    public User addNewfriend(Principal principal, int friendID) {
        User user = findByName(principal.getName());
        User friend = userGenericDao.find(friendID);
        user.getFriends().add(friend);
        userGenericDao.update(user);
        return user;
    }

    @Override
    public User deleteFriend(Principal principal, int friendID) {
        User user = findByName(principal.getName());
        User friend = userGenericDao.find(friendID);
        if (user.getFriends().contains(friend)) {
            user.getFriends().remove(friend);
            userGenericDao.update(user);
            return friend;
        } else {
            throw new UserExistException("No user with such id");
        }
    }

    @Override
    public List<UserDTO> getSubscribers(int userID) {
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

        return users.stream().map(userFacade::getUserProfile).collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getFriends(int userID) {
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

        return users.stream().map(userFacade::getUserProfile).collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(int userID) {
        User user = userGenericDao.find(userID);
        if (user != null) {
            UserDTO userDTO = userFacade.getUserProfile(user);
            userDTO.setPosts((user.getPosts().stream().map(postFacade::postToPostDTO).collect(Collectors.toList())));
            return userDTO;
        } else {
            throw new UserExistException("No user with such id");
        }
    }

    @Override
    public List<UserDTO> findallUsers() {
        return userGenericDao.findAll().stream().map(userFacade::getUserProfile).collect(Collectors.toList());
    }

    @Override
    public UserDTO openOwnPage(Principal principal) {
        User user = findByName(principal.getName());
        return userFacade.getUserProfile(user);
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
    public List<Group> getListGroup(Principal principal) {
        User user = findByName(principal.getName());
        return new ArrayList<>(user.getCommunities());
    }

}
