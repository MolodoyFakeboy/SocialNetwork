package com.social.network.controller.interfac;

import com.social.network.dto.UserDTO;
import com.social.network.model.Group;
import com.social.network.model.Role;
import com.social.network.payLoad.changePasswordRequest.ChangePasswordRequest;
import com.social.network.payLoad.response.MessageResponse;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.List;

public interface IUserController {

    ResponseEntity<String> setRole(int id, Role role);

    ResponseEntity<UserDTO> findUserDtoByName(String name);

    ResponseEntity<MessageResponse> setBio(Principal principal,String bio);

    ResponseEntity<MessageResponse> changePassword(ChangePasswordRequest changePasswordRequest);

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
