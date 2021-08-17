package com.social.network.Services;

import com.social.network.Configs.Config;
import com.social.network.Model.User;
import com.social.network.PayLoad.ChangePasswordRequest.ChangePasswordRequest;
import com.social.network.PayLoad.LoginRequest.LoginRequest;
import com.social.network.PayLoad.SignUpRequest.SignupRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Config.class)
class UserSerivceTest {

    private IUserService userService;

    @Autowired
    public UserSerivceTest(IUserService userService) {
        this.userService = userService;
    }

    @Test
    void createUser() {
        SignupRequest signupRequest = new SignupRequest("mamontov.SKAM", "cDeqwt2Ygd", "mamontov.artem51@example.net", Date.valueOf("1945-10-01"));
        User user = userService.createUser(signupRequest);

        Assertions.assertEquals(signupRequest.getUserName(),user.getUsername());
    }

    @Test
    void findByName() {
      String username = "mamontov.SKAM";
      User user = userService.findByName(username);

      Assertions.assertEquals(username,user.getUsername());
    }

    @Test
    void findByNamePassword() {
       LoginRequest loginRequest = new LoginRequest("mamontov.SKAM","cDeqwt2Ygd");
       User user = userService.findByNamePassword(loginRequest);

       Assertions.assertEquals(loginRequest.getUsername(),user.getUsername());
    }

    @Test
    void changePassowrd() {
        ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest("mamontov.SKAM","cDeqwt2Ygd","Skamer228");

        userService.changePassowrd(changePasswordRequest);
    }

    @Test
    void changeUserName() {
        int userID = 53;
        User user = userService.findById(userID);
        User user1 = userService.changeUserName(userID,"Main.Skamer");

        Assertions.assertNotEquals(user.getUsername(),user1.getUsername());
    }

    @Test
    void changeEmail() {
        int userID = 53;
        User user = userService.findById(userID);
        User user1 = userService.changeEmail(userID,"mamontov.artem51@gmail.com");

        Assertions.assertNotEquals(user.getEmail(),user1.getEmail());
    }

    @Test
    void addNewfriend() {
      int userID = 53;
      int friendID = 2;
      userService.addNewfriend(userID,friendID);
      User user = userService.findById(userID);

      Assertions.assertNotNull(user.getFriends());
    }

    @Test
    void deleteFriend() {
        int userID = 53;
        int friendID = 2;
        userService.deleteFriend(userID,friendID);
        User user = userService.findById(userID);

       Assertions.assertNull(user.getFriends());

    }

    @Test
    void getSubscribers() {
       int userID = 2;
       List<User>users = userService.getSubscribers(2);

        Assertions.assertNotNull(users);
    }

    @Test
    void getFriends() {
        int userID = 2;
        List<User>users = userService.getFriends(2);

        Assertions.assertNotNull(users);
    }


    @Test
    void findallUsers() {
      List<User>users = userService.findallUsers();

      Assertions.assertTrue(users.size() > 50);
    }

}