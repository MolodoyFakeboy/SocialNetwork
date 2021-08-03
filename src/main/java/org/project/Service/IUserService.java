package org.project.Service;

import org.project.Model.Role;
import org.project.Model.User;

import java.util.List;

public interface IUserService {

    void add(User user);

    User update(User user);

    void delete(int id);

    void setRole(int id,Role role);

    List<User> findAll();

    User find(int id);

    User findByName(String name);

    User findByNamePassword(String name,String password);

}
