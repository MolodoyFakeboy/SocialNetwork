package com.social.network.Services.Interfaces;

import com.social.network.Dto.UserDTO;
import com.social.network.Model.Post;
import com.social.network.Model.Role;
import com.social.network.Model.User;
import com.social.network.PayLoad.ChangePasswordRequest.ChangePasswordRequest;
import com.social.network.PayLoad.LoginRequest.LoginRequest;
import com.social.network.PayLoad.SignUpRequest.SignupRequest;

import java.security.Principal;
import java.util.List;


public interface IUserService {

    User createUser(SignupRequest signupRequest);

    void setRole(int id, Role role);

    UserDTO findUserDtoByName(String name);

    User findByNamePassword(LoginRequest loginRequest);

    User setBio(int id,String bio);

    User changePassowrd(ChangePasswordRequest changePasswordRequest);

    User changeUserName(Principal principal, String userName);

    User changeEmail(Principal principal, String email);

    User addNewfriend(Principal principal, int friendID);

    UserDTO findById(int userID);

    UserDTO openOwnPage(Principal principal);

    User deleteFriend(Principal principal, int friendID);

    List<UserDTO> getSubscribers(int userID);

    List<UserDTO> getFriends(int userID);

    List<UserDTO>findallUsers();

    User findByName(String name);

}
