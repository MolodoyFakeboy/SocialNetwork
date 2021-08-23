package com.social.network.Controller;

import com.social.network.Controller.Interfaces.IPostController;
import com.social.network.Dto.PostDTO;
import com.social.network.PayLoad.response.MessageResponse;
import com.social.network.Services.Interfaces.IPostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class PostController implements IPostController {

    private IPostService postService;

    public PostController(IPostService postService) {
        this.postService = postService;
    }

    @Override
    @PostMapping("post")
    public ResponseEntity<PostDTO> createNewPostFromUser(@RequestBody PostDTO post, Principal principal) {
        PostDTO postDTO = postService.createNewPostFromUser(post, principal);
        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("post/{postID}")
    public ResponseEntity<MessageResponse> deleatePost(@PathVariable int postID, Principal principal) {
        postService.deleatePost(postID, principal);
        return new ResponseEntity<>(new MessageResponse("Пост успешно удален"), HttpStatus.OK);
    }

    @Override
    @GetMapping("post/{postID}")
    public ResponseEntity<PostDTO> findPostByID(@PathVariable int postID) {
        PostDTO postDTO = postService.findPostByID(postID);
        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }

    @Override
    @GetMapping("news")
    public ResponseEntity<List<PostDTO>> openNews(Principal principal) {
        List<PostDTO> posts = postService.openNews(principal);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}
