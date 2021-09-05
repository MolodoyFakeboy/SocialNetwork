package com.social.network.service.interfac;

import com.social.network.dto.UserDTO;
import com.social.network.model.Group;
import com.social.network.model.Role;
import com.social.network.model.User;
import com.social.network.payLoad.changePasswordRequest.ChangePasswordRequest;
import com.social.network.payLoad.loginRequest.LoginRequest;
import com.social.network.payLoad.signUpRequest.SignupRequest;

import java.security.Principal;
import java.util.List;

public interface IUserService {

    User createUser(SignupRequest signupRequest);

    void setRole(int id, Role role);

    UserDTO findUserDtoByName(String name);

    User findByNamePassword(LoginRequest loginRequest);

    User setBio(Principal principal,String bio);

    User changePassword(ChangePasswordRequest changePasswordRequest);

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

    List<Group> getListGroup(Principal principal);

}
