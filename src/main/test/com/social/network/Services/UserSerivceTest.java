package com.social.network.Services;

import com.social.network.Configs.Config;
import com.social.network.Model.Role;
import com.social.network.Model.User;
import com.social.network.PayLoad.SignUpRequest.SignupRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

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
        SignupRequest signupRequest = new SignupRequest("mamontov.artem", "cDeqwt2Ygd", "mamontov.artem51@example.net", Date.valueOf("1945-10-01"));
        userService.createUser(signupRequest);
    }

    @Test
    void setRole() {
        Role role = new Role("USER");
        role.setIdRole(1);
        userService.setRole(1,role);
    }

    @Test
    void findByName() {

    }

    @Test
    void findByNamePassword() {

    }

    @Test
    void addNewfriend() {
        userService.addNewfriend(2,50);
    }

    @Test
    void acceptFriendRequest() {
       userService.friend(2);
    }
}