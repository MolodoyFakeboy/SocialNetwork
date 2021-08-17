package com.social.network.Services;

import com.social.network.Model.Post;
import com.social.network.Model.Role;
import com.social.network.Model.User;
import com.social.network.PayLoad.ChangePasswordRequest.ChangePasswordRequest;
import com.social.network.PayLoad.LoginRequest.LoginRequest;
import com.social.network.PayLoad.SignUpRequest.SignupRequest;

import java.util.List;
import java.util.Set;

public interface IUserService {

    User createUser(SignupRequest signupRequest);

    void setRole(int id, Role role);

    User findByName(String name);

    User findByNamePassword(LoginRequest loginRequest);

    User changePassowrd(ChangePasswordRequest changePasswordRequest);

    User changeUserName(int userID, String userName);

    User changeEmail(int userID, String email);

    User addNewfriend(int userID, int friendID);

    User findById(int userID);

    User deleteFriend(int userID, int friendID);

    List<User> getSubscribers(int userID);

    List<User> getFriends(int userID);

    List<User>findallUsers();

    Set<Post> openNews(int userID);

}
