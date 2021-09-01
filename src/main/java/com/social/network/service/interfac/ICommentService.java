package com.social.network.service.interfac;

import com.social.network.dto.CommentDTO;
import com.social.network.model.Comment;

import java.security.Principal;
import java.util.List;

public interface ICommentService {

    Comment writeCommentPublication(CommentDTO commentDTO, int publicationID, Principal principal);

    List<CommentDTO> openComments(int publicationID);
}
