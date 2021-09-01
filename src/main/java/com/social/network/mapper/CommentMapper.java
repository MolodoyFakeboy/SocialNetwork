package com.social.network.mapper;

import com.social.network.dto.CommentDTO;
import com.social.network.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface CommentMapper {
    @Mappings({
            @Mapping(target="sendTime", source="comment.dataCreate"),
            @Mapping(target="username", source="comment.user.username")
    })
    CommentDTO commentToCommentDto(Comment comment);
}
