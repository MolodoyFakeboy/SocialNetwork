package org.project.Controller;

import org.project.Model.AuthRequest;
import org.project.Model.Role;
import org.project.Model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserController {

    User add(User user);

    ResponseEntity<String> update(User user);

    ResponseEntity<String> delete(int id);

    List<User> findAll();

    User find(int id);

    ResponseEntity<String> setRole(int id, Role role);

    User findByName(String name);

    String auth(AuthRequest request);

}
