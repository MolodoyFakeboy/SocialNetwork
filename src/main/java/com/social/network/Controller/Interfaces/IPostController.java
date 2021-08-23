package com.social.network.Controller.Interfaces;

import com.social.network.Dto.PostDTO;
import com.social.network.Model.Post;
import com.social.network.PayLoad.response.MessageResponse;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.List;

public interface IPostController {

    ResponseEntity<PostDTO> createNewPostFromUser(PostDTO post, Principal principal);

    ResponseEntity<MessageResponse> deleatePost(int postID, Principal principal);

    ResponseEntity<PostDTO> findPostByID(int postID);

    ResponseEntity<List<PostDTO>> openNews(Principal principal);

}
