package com.social.network.service;

import com.social.network.dao.IUserDao;
import com.social.network.dto.UserDTO;
import com.social.network.exceptions.UserExistException;
import com.social.network.mapper.PostMapper;
import com.social.network.mapper.UserMapper;
import com.social.network.model.Group;
import com.social.network.model.Role;
import com.social.network.model.User;
import com.social.network.payLoad.changePasswordRequest.ChangePasswordRequest;
import com.social.network.payLoad.loginRequest.LoginRequest;
import com.social.network.payLoad.signUpRequest.SignupRequest;
import com.social.network.service.interfac.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserSerivce implements IUserService {

    private Logger log;

    private IUserDao userDao;

    private PasswordEncoder passwordEncoder;

    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    private PostMapper postMapper = Mappers.getMapper(PostMapper.class);

    @Autowired
    public UserSerivce(IUserDao userDao) {
        this.userDao = userDao;
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
            userDao.add(user);
            return user;
        } catch (Exception e) {
            log.error("Error during registration. {}", e.getMessage());
            throw new UserExistException("The user " + user.getUsername() + " already exist. Please check credentials");
        }

    }

    @Override
    public void setRole(int id, Role role) {
        User user = userDao.find(id);
        if (user == null) {
            throw new UserExistException("The user " + id + "not found");
        } else {
            user.setRole(role);
            userDao.update(user);
        }
    }

    @Override
    public User setBio(Principal principal, String bio) {
        User user = findByName(principal.getName());
        if (user == null) {
            throw new UserExistException("The user " + principal.getName() + "not found");
        } else {
            user.setBio(bio);
            userDao.update(user);
            return user;
        }
    }

    @Override
    public UserDTO findUserDtoByName(String name) {
        User timeUser = findByName(name);
        return userMapper.userToUserDto(timeUser);
    }

    @Override
    public UserDTO findById(int userID) {
        User user = userDao.find(userID);
        if (user != null) {
            UserDTO userDTO = userMapper.userToUserDto(user);
            userDTO.setPostsFromUser((user.getPosts().stream().map(postMapper::postToPostDto).collect(Collectors.toList())));
            return userDTO;
        } else {
            throw new UserExistException("No user with such id");
        }
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
    public User changePassword(ChangePasswordRequest changePasswordRequest) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(changePasswordRequest.getUserName());
        loginRequest.setPassword(changePasswordRequest.getOldPassword());
        User user = findByNamePassword(loginRequest);
        if (user != null) {
            user.setPassword(passwordEncoder.encode(changePasswordRequest.getPassword()));
            userDao.update(user);
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
            userDao.update(user);
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
            userDao.update(user);
            return user;
        } else {
            throw new UserExistException("No user with such id");
        }
    }

    @Override
    public UserDTO openOwnPage(Principal principal) {
        User user = findByName(principal.getName());
        UserDTO userDTO = userMapper.userToUserDto(user);
        userDTO.setPostsFromUser((user.getPosts().stream().map(postMapper::postToPostDto).collect(Collectors.toList())));
        return userDTO;
    }

    @Override
    public List<UserDTO> findallUsers() {
        return userDao.findAll().stream().map(userMapper::userToUserDto).collect(Collectors.toList());
    }

    @Override
    public User addNewfriend(Principal principal, int friendID) {
        User user = findByName(principal.getName());
        User friend = userDao.find(friendID);
        user.getFriends().add(friend);
        userDao.update(user);
        return user;
    }

    @Override
    public User deleteFriend(Principal principal, int friendID) {
        User user = findByName(principal.getName());
        User friend = userDao.find(friendID);
        if (user.getFriends().contains(friend)) {
            user.getFriends().remove(friend);
            userDao.update(user);
            return friend;
        } else {
            throw new UserExistException("No user with such id");
        }
    }

    @Override
    public List<UserDTO> getSubscribers(int userID) {
        User userTest = userDao.find(userID);

        EntityManager em = userDao.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> user = query.from(User.class);
        Join<User, User> friendsJoin = user.join("friends");
        Predicate userPredicate = cb.equal(friendsJoin.get("id"), userID);
        query.select(user).where(userPredicate);
        List<User> users = em.createQuery(query).getResultList();

        users.removeIf(us -> userTest.getFriends().contains(us));

        return users.stream().map(userMapper::userToUserDto).collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getFriends(int userID) {
        User userTest = userDao.find(userID);

        EntityManager em = userDao.getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> user = query.from(User.class);
        Join<User, User> friendsJoin = user.join("friends");
        Predicate userPredicate = cb.equal(friendsJoin.get("id"), userID);
        query.select(user).where(userPredicate);
        List<User> users = em.createQuery(query).getResultList();

        users.removeIf(us -> !userTest.getFriends().contains(us));

        return users.stream().map(userMapper::userToUserDto).collect(Collectors.toList());
    }


    @Override
    public User findByName(String name) {
        User timeUser = null;
        try {
            timeUser = userDao.findByName(name);
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
