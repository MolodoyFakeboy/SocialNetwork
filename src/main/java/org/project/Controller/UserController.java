package org.project.Controller;

import org.project.Configs.jwt.JwtProvider;
import org.project.Exeception.IdIncorrectData;
import org.project.Model.AuthRequest;
import org.project.Model.Role;
import org.project.Model.User;
import org.project.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class UserController implements IUserController {

    private IUserService userService;

    private JwtProvider jwtProvider;

    @Autowired
    public UserController(IUserService userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @Override
    @PostMapping("register")
    public User add(@RequestBody User user) {
        userService.add(user);
        return user;
    }

    @Override
    @PutMapping("user")
    public ResponseEntity<String> update(@RequestBody User user) {
        userService.update(user);
        return new ResponseEntity<>("Информация о пользователя успешно обновлена" , HttpStatus.OK);
    }

    @Override
    @DeleteMapping("user/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
         userService.delete(id);
        return new ResponseEntity<>("Пользователь удален" , HttpStatus.OK);
    }

    @Override
    @GetMapping("users")
    public List<User> findAll() {
        return userService.findAll();
    }

    @Override
    @PutMapping("userRole/{id}")
    public ResponseEntity<String> setRole(@PathVariable int id, @RequestBody Role role) {
        userService.setRole(id, role);
        return new ResponseEntity<>("Статус пользователя успешно установлен" , HttpStatus.OK);
    }

    @Override
    @GetMapping("userforName/{name}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public User findByName(@PathVariable String name) {
         return userService.findByName(name);
    }

    @Override
    @GetMapping("userforID/{id}")
    public User find(@PathVariable int id) {
        User user = userService.find(id);
        if (user == null) {
            throw new NoSuchElementException("There is no guest with such id = " + id+ " in shema hotel");
        }
        return user;
    }

    @Override
    @PostMapping("/auth")
    public String auth(@RequestBody AuthRequest request) {
        User user = userService.findByNamePassword(request.getLogin(), request.getPassword());
        if(user == null){
            throw new NullPointerException("Неверный логин или пароль, попробуйте еще раз");
        }
        String token = jwtProvider.generateToken(user.getUsername());
        return token;
    }

    @ExceptionHandler
    public ResponseEntity<IdIncorrectData> handleException(NoSuchElementException exception){
        IdIncorrectData idIncorrectData = new IdIncorrectData();
        idIncorrectData.setInfo(exception.getMessage());
        return new ResponseEntity<>(idIncorrectData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<IdIncorrectData> handleException(NullPointerException exception){
        IdIncorrectData idIncorrectData = new IdIncorrectData();
        idIncorrectData.setInfo(exception.getMessage());
        return new ResponseEntity<>(idIncorrectData, HttpStatus.NOT_FOUND);
    }

}
