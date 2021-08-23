package com.social.network.Services.Interfaces;

import com.social.network.Dto.PostDTO;
import com.social.network.Model.Post;

import java.security.Principal;
import java.util.List;

public interface IPostService {

    PostDTO createNewPostFromUser(PostDTO post, Principal principal);

    boolean deleatePost(int postID,Principal principal);

    PostDTO findPostByID(int postID);

    List<PostDTO> openNews(Principal principal);

}
