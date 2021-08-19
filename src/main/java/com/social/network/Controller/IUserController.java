package com.social.network.Controller;

import com.social.network.Dto.UserDTO;
import com.social.network.Model.Post;
import com.social.network.Model.Role;
import com.social.network.Model.User;
import com.social.network.PayLoad.ChangePasswordRequest.ChangePasswordRequest;
import com.social.network.PayLoad.LoginRequest.LoginRequest;
import com.social.network.PayLoad.SignUpRequest.SignupRequest;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.List;

public interface IUserController {

    ResponseEntity<String> setRole(int id, Role role);

    ResponseEntity<UserDTO> findUserDtoByName(String name);

    ResponseEntity<String> setBio(int id,String bio);

    ResponseEntity<String> changePassowrd(ChangePasswordRequest changePasswordRequest);

    ResponseEntity<String> changeUserName(Principal principal, String userName);

    ResponseEntity<String> changeEmail(Principal principal, String email);

    ResponseEntity<String>addNewfriend(Principal principal, int friendID);

    ResponseEntity<UserDTO> findById(int userID);

    User openOwnPage(Principal principal);

    User deleteFriend(Principal principal, int friendID);

    List<UserDTO> getSubscribers(int userID);

    List<UserDTO> getFriends(int userID);

    List<UserDTO>findallUsers();

    List<Post> openNews(int userID);

    User findByName(String name);
}
