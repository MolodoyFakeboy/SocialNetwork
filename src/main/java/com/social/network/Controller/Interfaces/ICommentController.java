package com.social.network.Controller.Interfaces;

import com.social.network.Dto.CommentDTO;
import com.social.network.Model.Comment;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.List;

public interface ICommentController {

    ResponseEntity<Comment> writeCommentPublication(CommentDTO commentDTO, int publicationID, Principal principal);

    ResponseEntity<List<CommentDTO>> openComments(int publicationID);
}
