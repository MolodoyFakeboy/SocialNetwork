package com.social.network.controller.interfac;

import com.social.network.dto.CommentDTO;
import com.social.network.model.Comment;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.List;

public interface ICommentController {

    ResponseEntity<Comment> writeCommentPublication(CommentDTO commentDTO, int publicationID, Principal principal);

    ResponseEntity<List<CommentDTO>> openComments(int publicationID);
}
