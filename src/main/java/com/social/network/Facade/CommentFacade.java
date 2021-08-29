package com.social.network.Facade;

import com.social.network.Dto.CommentDTO;
import com.social.network.Model.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentFacade {

    public CommentDTO commentDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setMessage(comment.getMessage());
        commentDTO.setSendTime(comment.getDataCreate());
        commentDTO.setUsername(comment.getUser().getUsername());
        return commentDTO;
    }
}
