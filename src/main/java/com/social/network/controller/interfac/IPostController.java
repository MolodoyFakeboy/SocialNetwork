package com.social.network.controller.interfac;

import com.social.network.dto.PostDTO;
import com.social.network.payLoad.response.MessageResponse;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.List;

public interface IPostController {

    ResponseEntity<PostDTO> createNewPostFromUser(PostDTO post, Principal principal);

    ResponseEntity<MessageResponse> deleatePost(int postID, Principal principal);

    ResponseEntity<PostDTO> findPostByID(int postID);

    ResponseEntity<List<PostDTO>> openNews(Principal principal);

}
