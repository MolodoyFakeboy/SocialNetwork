package org.project.Service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.project.Configs.ConfigTest;
import org.project.Dao.UserDao;
import org.project.Model.Role;
import org.project.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ConfigTest.class)
class UserServiceTest {

    @Mock
    private UserDao userDao;

    private UserService userService;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userDao);
        userService.setPasswordEncoder(passwordEncoder);
        Mockito.when(userDao.getEntityManager()).thenReturn(entityManager);
    }

    @Test
    void addnewUser() {
        User user = new User("Zahar", "Zahar");
        userService.add(user);
        Mockito.verify(userDao).add(user);
        Mockito.verify(userDao, Mockito.times(1)).add(user);

    }

    @Test
    void updateInfoAboutUser() {
        User user = new User("Zahar", "Zahar");
        user.setUserID(10);
        Mockito.when(userDao.update(user)).thenReturn(user);
        userService.update(user);
        Mockito.verify(userDao).update(user);
    }

    @Test
    void deleteUser() {
        User user = new User("Zahar", "Zahar");
        user.setUserID(10);
        userService.delete(user.getUserID());
        Mockito.verify(userDao).delete(user.getUserID());
        Mockito.verify(userDao, Mockito.times(1)).delete(user.getUserID());
    }

    @Test
    void setRoletoUser() {
        User user = new User("Zahar", "Zahar");
        user.setUserID(10);
        Role role = new Role("ADMIN");
        Mockito.when(userDao.update(user)).thenReturn(user);
        Mockito.when(userDao.find(user.getUserID())).thenReturn(user);
        userService.setRole(user.getUserID(), role);
        Mockito.verify(userDao).update(user);
    }

    @Test
    void findAllUsers() {
        User user = new User("Zahar", "Zahar");
        User user2 = new User("Zahar", "Zahar2");
        List<User> userList = Arrays.asList(user, user2);
        Mockito.when(userDao.findAll()).thenReturn(userList);
        List<User> users = userService.findAll();
        Mockito.verify(userDao).findAll();

        Assertions.assertEquals(userList, users);
    }

    @Test
    void find_onlyOneUser() {
        User user = new User("Zahar", "Zahar");
        user.setUserID(10);
        Mockito.when(userDao.find(user.getUserID())).thenReturn(user);
        User timeUser = userService.find(user.getUserID());
        Mockito.verify(userDao).find(user.getUserID());

        Assertions.assertEquals(user, timeUser);
    }

    @Test
    void findByNameUser() {
        User user = new User("Zahar", "Zahar");
        user.setUserID(10);
        User timeUser = userService.findByName(user.getUsername());

        Assertions.assertEquals(user.getUsername(), timeUser.getUsername());
    }

    @Test
    void userFindByNamePassword_andResultWillBeNull() {
        User user = new User("Zahar", "Zahar");
        user.setUserID(10);
        User timeUser = userService.findByNamePassword(user.getUsername(), user.getPassword());

        Assertions.assertEquals(null, timeUser);
    }

}