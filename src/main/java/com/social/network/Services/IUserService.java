package com.social.network.Services;

import com.social.network.Model.Role;
import com.social.network.Model.User;
import com.social.network.PayLoad.ChangePasswordRequest.ChangePasswordRequest;
import com.social.network.PayLoad.SignUpRequest.SignupRequest;

import java.util.List;

public interface IUserService {

    User createUser(SignupRequest signupRequest);

    void setRole(int id, Role role);

    User findByName(String name);

    User findByNamePassword(String name, String password);

    User changePassowrd(ChangePasswordRequest changePasswordRequest);

    User changeUserName(int userID, String userName);

    User changeEmail(int userID, String email);

    User addNewfriend(int userID, int friendID);

    User findById(int userID);

    User deleteFriend(int userID, int friendID);

    List<User> Subscribers(int userID);

    List<User> friend(int userID);

}
