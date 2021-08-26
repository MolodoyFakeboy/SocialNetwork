package com.social.network.Services.Interfaces;

import com.social.network.Dto.CommentDTO;
import com.social.network.Model.Comment;

import java.security.Principal;
import java.util.List;

public interface ICommentService {

    Comment writeCommentPublication(CommentDTO commentDTO, int publicationID, Principal principal);

    List<CommentDTO> openComments(int publicationID);
}
