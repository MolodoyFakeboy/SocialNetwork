package com.social.network.controller;

import com.social.network.controller.interfac.ICommentController;
import com.social.network.dto.CommentDTO;
import com.social.network.model.Comment;
import com.social.network.service.interfac.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class CommentController implements ICommentController {

    private ICommentService commentService;

    @Autowired
    public CommentController(ICommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    @PostMapping("comment/{publicationID}")
    public ResponseEntity<Comment> writeCommentPublication(@RequestBody CommentDTO commentDTO, @PathVariable int publicationID, Principal principal) {
        Comment comment = commentService.writeCommentPublication(commentDTO, publicationID, principal);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @Override
    @GetMapping("comments/{publicationID}")
    public ResponseEntity<List<CommentDTO>> openComments(@PathVariable int publicationID) {
        List<CommentDTO>comments = commentService.openComments(publicationID);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
