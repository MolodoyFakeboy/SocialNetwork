package com.social.network.service.interfac;

import com.social.network.dto.PostDTO;

import java.security.Principal;
import java.util.List;

public interface IPostService {

    PostDTO createNewPostFromUser(PostDTO post, Principal principal);

    boolean deleatePost(int postID,Principal principal);

    PostDTO findPostByID(int postID);

    List<PostDTO> openNews(Principal principal);

}
