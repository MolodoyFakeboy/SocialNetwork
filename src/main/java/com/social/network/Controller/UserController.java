//package com.social.network.Controller;
//
//import com.social.network.Dto.UserDTO;
//import com.social.network.Model.Post;
//import com.social.network.Model.Role;
//import com.social.network.Model.User;
//import com.social.network.PayLoad.ChangePasswordRequest.ChangePasswordRequest;
//import com.social.network.Services.Interfaces.IUserService;
//import com.social.network.jwt.JwtProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.security.Principal;
//import java.util.List;
//
//@RestController
//public class UserController implements IUserController {
//
//    private IUserService userService;
//
//    private JwtProvider jwtProvider;
//
//    @Autowired
//    public UserController(IUserService userService, JwtProvider jwtProvider) {
//        this.userService = userService;
//        this.jwtProvider = jwtProvider;
//    }
//
//
//    @Override
//    @PutMapping("userRole/{id}")
//    public ResponseEntity<String> setRole(@PathVariable int id, @RequestBody Role role) {
//        userService.setRole(id, role);
//        return new ResponseEntity<>("Статус пользователя успешно установлен" , HttpStatus.OK);
//    }
//
//    @Override
//    @GetMapping("searchUser/{name}")
//    public ResponseEntity<UserDTO> findUserDtoByName(@PathVariable String name) {
//        UserDTO userDTO = userService.findUserDtoByName(name);
//        return new ResponseEntity<>(userDTO , HttpStatus.OK);
//    }
//
//
//    @Override
//    @PutMapping("userBio/{id}/{bio}")
//    public ResponseEntity<String> setBio(@PathVariable int id, @PathVariable String bio) {
//        User user = userService.setBio(id, bio);
//        return new ResponseEntity<>("Статус пользователя " + user.getUsername() + "обновлен " , HttpStatus.OK);
//    }
//
//    @Override
//    public ResponseEntity<String> changePassowrd(ChangePasswordRequest changePasswordRequest) {
//        return null;
//    }
//
//    @Override
//    public ResponseEntity<String>changeUserName(Principal principal, String userName) {
//        return null;
//    }
//
//    @Override
//    public ResponseEntity<String> changeEmail(Principal principal, String email) {
//        return null;
//    }
//
//    @Override
//    public ResponseEntity<String> addNewfriend(Principal principal, int friendID) {
//        return null;
//    }
//
//    @Override
//    public ResponseEntity<UserDTO>findById(int userID) {
//        return null;
//    }
//
//    @Override
//    public User openOwnPage(Principal principal) {
//        return null;
//    }
//
//    @Override
//    public User deleteFriend(Principal principal, int friendID) {
//        return null;
//    }
//
//    @Override
//    public List<UserDTO> getSubscribers(int userID) {
//        return null;
//    }
//
//    @Override
//    public List<UserDTO> getFriends(int userID) {
//        return null;
//    }
//
//    @Override
//    public List<UserDTO> findallUsers() {
//        return null;
//    }
//
//    @Override
//    public List<Post> openNews(int userID) {
//        return null;
//    }
//
//    @Override
//    public User findByName(String name) {
//        return null;
//    }
//}
