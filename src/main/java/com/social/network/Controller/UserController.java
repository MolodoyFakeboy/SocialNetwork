package com.social.network.Controller;

import com.social.network.Controller.Interfaces.IUserController;
import com.social.network.Dto.UserDTO;
import com.social.network.Model.Group;
import com.social.network.Model.Role;
import com.social.network.Model.User;
import com.social.network.PayLoad.ChangePasswordRequest.ChangePasswordRequest;
import com.social.network.PayLoad.response.MessageResponse;
import com.social.network.Services.Interfaces.IUserService;
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
    @PutMapping("userBio/{id}/{bio}")
    public ResponseEntity<MessageResponse> setBio(@PathVariable int id, @PathVariable String bio) {
        User user = userService.setBio(id, bio);
        return new ResponseEntity<>(new MessageResponse("Статус пользователя " + user.getUsername() + "обновлен ") , HttpStatus.OK);
    }

    @Override
    @PutMapping("userPassword")
    public ResponseEntity<MessageResponse> changePassowrd(@RequestBody ChangePasswordRequest changePasswordRequest) {
        User user = userService.changePassowrd(changePasswordRequest);
        return new ResponseEntity<>(new MessageResponse ("Пароль " + user.getUsername() + "успешно обновлен ") , HttpStatus.OK);
    }

    @Override
    @PutMapping("userName/{userName}")
    public ResponseEntity<MessageResponse>changeUserName(Principal principal, @PathVariable String userName) {
        User user = userService.changeUserName(principal, userName);
        return new ResponseEntity<>(new MessageResponse ("Логин " + user.getUsername() + "успешно обновлен ") , HttpStatus.OK);
    }

    @Override
    @PutMapping("userEmail/{email}")
    public ResponseEntity<MessageResponse> changeEmail(Principal principal, @PathVariable String email) {
        User user = userService.changeEmail(principal, email);
        return new ResponseEntity<>(new MessageResponse ("Почта " + user.getUsername() + "успешно обновлен ") , HttpStatus.OK);
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
