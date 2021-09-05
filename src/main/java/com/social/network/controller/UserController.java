package com.social.network.controller;

import com.social.network.controller.interfac.IUserController;
import com.social.network.dto.UserDTO;
import com.social.network.model.Group;
import com.social.network.model.Role;
import com.social.network.model.User;
import com.social.network.payLoad.changePasswordRequest.ChangePasswordRequest;
import com.social.network.payLoad.response.MessageResponse;
import com.social.network.service.interfac.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class UserController implements IUserController {

    private IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<UserDTO> openOwnPage(Principal principal) {
        UserDTO userDTO = userService.openOwnPage(principal);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @Override
    @PutMapping("userRole/{id}")
    public ResponseEntity<String> setRole(@PathVariable int id, @RequestBody Role role) {
        userService.setRole(id, role);
        return new ResponseEntity<>("Статус пользователя успешно установлен" , HttpStatus.OK);
    }

    @Override
    @GetMapping("searchUser/{name}")
    public ResponseEntity<UserDTO> findUserDtoByName(@PathVariable String name) {
        UserDTO userDTO = userService.findUserDtoByName(name);
        return new ResponseEntity<>(userDTO , HttpStatus.OK);
    }

    @Override
    @PutMapping("userBio/{bio}")
    public ResponseEntity<MessageResponse> setBio(Principal principal, @PathVariable String bio) {
        User user = userService.setBio(principal, bio);
        return new ResponseEntity<>(new MessageResponse("Статус пользователя " + user.getUsername() + " обновлен ") , HttpStatus.OK);
    }

    @Override
    @PutMapping("userPassword")
    public ResponseEntity<MessageResponse> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        User user = userService.changePassword(changePasswordRequest);
        return new ResponseEntity<>(new MessageResponse ("Пароль " + user.getUsername() + " успешно обновлен ") , HttpStatus.OK);
    }

    @Override
    @PutMapping("userName/{userName}")
    public ResponseEntity<MessageResponse>changeUserName(Principal principal, @PathVariable String userName) {
        User user = userService.changeUserName(principal, userName);
        return new ResponseEntity<>(new MessageResponse
                ("Логин " + user.getUsername() + " успешно обновлен. Повторно авторизируйтесь") , HttpStatus.OK);
    }

    @Override
    @PutMapping("userEmail/{email}")
    public ResponseEntity<MessageResponse> changeEmail(Principal principal, @PathVariable String email) {
        User user = userService.changeEmail(principal, email);
        return new ResponseEntity<>(new MessageResponse ("Почта " + user.getUsername() + " успешно обновлен ") , HttpStatus.OK);
    }

    @Override
    @PutMapping("userFriend/{friendID}")
    public ResponseEntity<MessageResponse> addNewfriend(Principal principal, @PathVariable int friendID) {
        userService.addNewfriend(principal,friendID);
        return new ResponseEntity<>(new MessageResponse ("Заявка успешно отправлена") , HttpStatus.OK);
    }

    @Override
    @PutMapping("userDeleteFriend/{friendID}")
    public ResponseEntity<MessageResponse> deleteFriend(Principal principal, @PathVariable int friendID) {
       userService.deleteFriend(principal, friendID);
        return new ResponseEntity<>(new MessageResponse ("Друг успешно удален") , HttpStatus.OK);
    }

    @Override
    @GetMapping("user/{userID}")
    public ResponseEntity<UserDTO>findById(@PathVariable int userID) {
       UserDTO userDTO = userService.findById(userID);
        return new ResponseEntity<>(userDTO , HttpStatus.OK);
    }

    @Override
    @GetMapping("userSubscribers/{userID}")
    public ResponseEntity<List<UserDTO>> getSubscribers(@PathVariable int userID) {
        List<UserDTO> subscribers = userService.getSubscribers(userID);
        return new ResponseEntity<>(subscribers , HttpStatus.OK);
    }

    @Override
    @GetMapping("userFriends/{userID}")
    public ResponseEntity<List<UserDTO>> getFriends(@PathVariable int userID) {
        List<UserDTO> subcribers = userService.getFriends(userID);
        return new ResponseEntity<>(subcribers , HttpStatus.OK);
    }

    @Override
    @GetMapping("users")
    public ResponseEntity<List<UserDTO>> findallUsers() {
        List<UserDTO> users = userService.findallUsers();
        return new ResponseEntity<>(users , HttpStatus.OK);
    }

    @Override
    @GetMapping("user/group")
    public ResponseEntity<List<Group>> getListGroup(Principal principal){
       List<Group>groups = userService.getListGroup(principal);
       return new ResponseEntity<>(groups,HttpStatus.OK);
    }

}
