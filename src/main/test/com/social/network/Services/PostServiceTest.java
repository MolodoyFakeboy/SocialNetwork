package com.social.network.Services;

import com.social.network.Configs.Config;
import com.social.network.Model.Post;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Config.class)
class PostServiceTest {

    private IPostService postService;

    @Autowired
    public PostServiceTest(IPostService postService) {
        this.postService = postService;
    }

    @Test
    void createNewPostFromUser() {
//        Post post = new Post("ывфвфвыfxf");
//        postService.createNewPostFromUser(post,1);
    }
}