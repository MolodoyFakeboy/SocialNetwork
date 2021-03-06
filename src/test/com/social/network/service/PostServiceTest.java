package com.social.network.service;

import com.social.network.config.TestConfig;
import com.social.network.dto.PostDTO;
import com.social.network.service.interfac.IPostService;
import com.social.network.testModel.TestPrincipal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
class PostServiceTest {

    private IPostService postService;

    @Autowired
    public PostServiceTest(IPostService postService) {
        this.postService = postService;
    }

    @Test
    void createNewPostFromUser() {
        PostDTO postDTO = new PostDTO();
        postDTO.setTitle("Я начал тестировать свой проект!");
        TestPrincipal testPrincipal = new TestPrincipal();
        PostDTO postDTO1 = postService.createNewPostFromUser(postDTO,testPrincipal);

        Assertions.assertEquals(postDTO1.getUsername(),postDTO.getUsername());
    }


    @Test
    void deleatePost() {
        TestPrincipal testPrincipal = new TestPrincipal();
        int postID = 50; // Брать данные из бд
        boolean result = postService.deleatePost(postID,testPrincipal);

        Assertions.assertTrue(result);
    }

    @Test
    void findPostByID() {
        PostDTO postDTO = postService.findPostByID(50);

        Assertions.assertNotNull(postDTO);
    }

    @Test
    void openNews() {
        TestPrincipal testPrincipal = new TestPrincipal();
        List<PostDTO> posts = postService.openNews(testPrincipal);
        posts.forEach(System.out::println);

        Assertions.assertNotNull(posts);
    }
}