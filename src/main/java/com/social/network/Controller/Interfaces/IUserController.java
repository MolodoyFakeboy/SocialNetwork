package com.social.network.Controller.Interfaces;

import com.social.network.Dto.UserDTO;
import com.social.network.Model.Group;
import com.social.network.Model.Post;
import com.social.network.Model.Role;
import com.social.network.Model.User;
import com.social.network.PayLoad.ChangePasswordRequest.ChangePasswordRequest;
import com.social.network.PayLoad.LoginRequest.LoginRequest;
import com.social.network.PayLoad.SignUpRequest.SignupRequest;
import com.social.network.PayLoad.response.MessageResponse;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.List;

public interface IUserController {

    ResponseEntity<String> setRole(int id, Role role);

    ResponseEntity<UserDTO> findUserDtoByName(String name);

    ResponseEntity<MessageResponse> setBio(int id,String bio);

    ResponseEntity<MessageResponse> changePassowrd(ChangePasswordRequest changePasswordRequest);

    ResponseEntity<MessageResponse> changeUserName(Principal principal, String userName);

    ResponseEntity<MessageResponse> changeEmail(Principal principal, String email);

    ResponseEntity<MessageResponse>addNewfriend(Principal principal, int friendID);

    ResponseEntity<UserDTO> findById(int userID);

    ResponseEntity<UserDTO> openOwnPage(Principal principal);

    ResponseEntity<MessageResponse> deleteFriend(Principal principal, int friendID);

    ResponseEntity<List<UserDTO>> getSubscribers(int userID);

    ResponseEntity<List<UserDTO>>  getFriends(int userID);

    ResponseEntity<List<UserDTO>> findallUsers();

    ResponseEntity<List<Group>> getListGroup(Principal principal);

}
