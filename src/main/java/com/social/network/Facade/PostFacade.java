package com.social.network.Facade;

import com.social.network.Dto.PostDTO;
import com.social.network.Model.Post;
import org.springframework.stereotype.Component;

@Component
public class PostFacade {

    public PostDTO postToPostDTO(Post post){
        PostDTO postDTO = new PostDTO();
        postDTO.setCreatedDate(post.getCreatedDate());
        postDTO.setTitle(post.getTitle());
        postDTO.setUsername(post.getUser().getUsername());
        postDTO.setPostID(post.getIdPost());
        return postDTO;
    }
}
