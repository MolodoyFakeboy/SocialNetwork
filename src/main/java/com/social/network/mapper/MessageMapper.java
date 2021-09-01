package com.social.network.mapper;

import com.social.network.dto.MessageDTO;
import com.social.network.model.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface MessageMapper {
    @Mapping(target="username", source="message.user.username")
    MessageDTO messageToMessageDto(Message message);
}
