package com.social.network.Services;

import com.social.network.Model.Post;
import com.social.network.Model.User;

import java.security.Principal;

public interface IPostService {

    Post createNewPostFromUser(Post post, Principal principal);

    boolean deleatePost(int postID);

    Post findPostByID(int postID);

}
