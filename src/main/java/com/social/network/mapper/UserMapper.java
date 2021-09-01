package com.social.network.mapper;

import com.social.network.dto.UserDTO;
import com.social.network.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    @Mapping(target="userID", source="user.id")
    UserDTO userToUserDto(User user);
}
