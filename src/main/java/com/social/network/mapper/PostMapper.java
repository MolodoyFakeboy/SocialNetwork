package com.social.network.mapper;

import com.social.network.dto.PostDTO;
import com.social.network.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface PostMapper {
    @Mappings({
            @Mapping(target="postID", source="post.idPost"),
            @Mapping(target="username", source="post.user.username")
    })
    PostDTO postToPostDto(Post post);
}
