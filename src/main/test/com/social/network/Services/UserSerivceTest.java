package com.social.network.Services;

import com.social.network.Config.TestConfig;
import com.social.network.Configs.Config;
import com.social.network.Dao.UserDao;
import com.social.network.Dto.UserDTO;
import com.social.network.Facade.UserFacade;
import com.social.network.Model.User;
import com.social.network.PayLoad.ChangePasswordRequest.ChangePasswordRequest;
import com.social.network.PayLoad.LoginRequest.LoginRequest;
import com.social.network.PayLoad.SignUpRequest.SignupRequest;
import com.social.network.Services.Interfaces.IUserService;
import com.social.network.TestModel.TestPrincipal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
class UserSerivceTest {

    @Mock
    private UserDao userDao;

    private UserSerivce userService;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserFacade userFacade;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserSerivce(userDao);
        userService.setPasswordEncoder(passwordEncoder);
        userService.setUserFacade(userFacade);
        Mockito.when(userDao.getEntityManager()).thenReturn(entityManager);
    }

    @Test
    void createUser() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUserName("mamontov.Skam");
        signupRequest.setBirthday(Date.valueOf("1945-10-01"));
        signupRequest.setPassword("cDeqwt2Ygd");
        User user = userService.createUser(signupRequest);
        Mockito.verify(userDao).add(user);
        Mockito.verify(userDao, Mockito.times(1)).add(user);

        Assertions.assertEquals(signupRequest.getUserName(),user.getUsername());
    }

    @Test
    void findByName() {
      String username = "schessor0";
      User user = userService.findByName(username);

      Assertions.assertEquals(username,user.getUsername());
    }


    @Test
    void findByNamePassword() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("schessor0");
        loginRequest.setPassword("Test");
       User user = userService.findByNamePassword(loginRequest);

       Assertions.assertEquals(loginRequest.getUsername(),user.getUsername());
    }

    @Test
    void changePassowrd() {
        ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest();
        changePasswordRequest.setOldPassword("cDeqwt2Ygd");
        changePasswordRequest.setUserName("mamontov.ur");
        changePasswordRequest.setPassword("Skamer228");
        User user = userService.changePassowrd(changePasswordRequest);
        Mockito.verify(userDao).update(user);
        Mockito.verify(userDao, Mockito.times(1)).update(user);

        Assertions.assertNotEquals(changePasswordRequest.getOldPassword(),user.getPassword());
    }

    @Test
    void changeUserName() {
        TestPrincipal testPrincipal = new TestPrincipal();
        User user = userService.changeUserName(testPrincipal,"name");
        Mockito.when(userDao.update(user)).thenReturn(user);
        Mockito.verify(userDao).update(user);
        Mockito.verify(userDao, Mockito.times(1)).update(user);
    }

    @Test
    void changeEmail() {
        TestPrincipal testPrincipal = new TestPrincipal();
        User user = userService.changeEmail(testPrincipal,"mamontov.artem51@gmail.com");
        Mockito.when(userDao.update(user)).thenReturn(user);
        Mockito.verify(userDao).update(user);
        Mockito.verify(userDao, Mockito.times(1)).update(user);

        Assertions.assertEquals(testPrincipal.getName(),user.getUsername());
    }

    @Test
    void addNewfriend() {
        TestPrincipal testPrincipal = new TestPrincipal();
        int friendID = 2;
        User user = userService.addNewfriend(testPrincipal, friendID);
        Mockito.when(userDao.update(user)).thenReturn(user);
        Mockito.verify(userDao).update(user);
        Mockito.verify(userDao, Mockito.times(1)).update(user);

        Assertions.assertNotNull(user.getFriends());
    }

    @Test
    void getSubscribers(@Autowired IUserService userService) {
       int userID = 2;
       List<UserDTO> users = userService.getSubscribers(userID);
       users.forEach(System.out::println);
        Assertions.assertNotNull(users);
    }

    @Test
    void getFriends(@Autowired IUserService userService) {
        int userID = 2;
        List<UserDTO> users = userService.getFriends(userID);
        users.forEach(System.out::println);

        Assertions.assertNotNull(users);
    }

    @Test
    void findallUsers(@Autowired IUserService userService) {
        List<UserDTO> users = userService.findallUsers();

        Assertions.assertTrue(users.size() > 50);
    }

    @Test
    void openOwnPage() {
        TestPrincipal testPrincipal = new TestPrincipal();
        UserDTO userDTO = userService.openOwnPage(testPrincipal);

        Assertions.assertEquals(testPrincipal.getName(),userDTO.getUsername());
    }
}