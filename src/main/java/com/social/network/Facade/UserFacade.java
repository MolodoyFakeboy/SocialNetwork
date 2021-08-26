package com.social.network.Facade;

import com.social.network.Dto.UserDTO;
import com.social.network.Model.User;
import org.springframework.stereotype.Component;

@Component
public class UserFacade {

    public UserDTO getUserProfile(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setBirthday(user.getBirthday());
        userDTO.setBio(user.getBio());
        userDTO.setBirthday(user.getBirthday());
        userDTO.setUserID(user.getId());
        return userDTO;
    }
}
